import React, { useEffect, useState } from 'react';
import Typography from '../../components/Typography/Typography';
import TablePartido from '../../components/Tables/TablePartido';
import PartidoModal from '../../components/Modals/PartidoModal';
import { Button } from 'react-bootstrap';
import httpClient from "../../lib/httpClient";

const partidoInit = {
  fechaComienzo: '',
  estado: 'NO_INICIADO',
  jugadorLocal: {
    id: -1,
    nombre: '',
    puntos: 0,
  },
  jugadorVisitante: {
    id: -1,
    nombre: '',
    puntos: 0,
  },
  cancha: {
    id: -1,
    nombre: '',
    direccion: '',
  }
};
const dateOptions = {
  year: 'numeric',
  month: 'numeric',
  day: 'numeric',
  hour: 'numeric',
  minute: 'numeric',
  hour12: false,
};


const Partido = () => {
  const [partidosList, setPartidosList] = useState([]);
  const [listaJugadores, setJugadoresList] = useState([]);
  const [listaCanchas, setCanchasList] = useState([]);
  const [partidoData, setPartidoData] = useState(partidoInit);
  const [isEdit, setIsEdit] = useState(false);
  const [hasErrorInForm, setHasErrorInForm] = useState(false);
  const [openModal, setOpenModal] = useState(false);
  const [errorMsg, setErrorMsg] = useState('');

  useEffect(() => {
    getPartidos();
    getJugadores();
    getCanchas();
  }, []);

  // Functions
  //Usar esta funcion para convertir el string 'fechaComienzo' a Date
  const stringToDate = (dateString) => {
    try {
      const [fecha, hora] = dateString.split(' ');
      const [dd, mm, yyyy] = fecha.split('/');
      if (hora !== undefined) {
        if (hora.includes(':')) {
          const [hh, mins] = hora.split(':');
          return new Date(yyyy, mm - 1, dd, hh, mins);
        }
      }
      return new Date(yyyy, mm - 1, dd);
    } catch (err) {
      console.log(`stringToDate error formateando la fecha: ${err}`);
      return null;
    }
  };

  const validatePartido = () => {
    if (partidoData.jugadorLocal.id === partidoData.jugadorVisitante.id) {
      setErrorMsg('Los jugadores local y visitante no pueden ser iguales');
      return false;
    }

    const date = stringToDate(partidoData.fechaComienzo);

    if (!(date instanceof Date) || isNaN(date.getTime())) {
      setErrorMsg('El formato de la fecha/hora de inicio no es válido');
      return false;
    }

    if (date < new Date(Date.now())) {
      setErrorMsg('La fecha/hora de inicio debe ser mayor o igual a la fecha/hora actual');
      return false;
    }

    return true;
  };

  // Buttons
  const handleEditPartido = (editData, event) => {
    event.preventDefault();
    handleOpenModal(true, editData);
  };

  const handleDeletePartido = (id, event) => {
    event.preventDefault();
    if (window.confirm('Estas seguro?')) {
      borrarPartido(id);
    }
  };

  // Modal
  const handleOpenModal = (editarPartido = false, partidoToEdit = null) => {
    setIsEdit(editarPartido);

    if (editarPartido) {
      setPartidoData(partidoToEdit);
    }

    setOpenModal(true);
  };

  const handleCloseModal = () => {
    setOpenModal(false);
    setIsEdit(false);
    setHasErrorInForm(false);
    setPartidoData(partidoInit);
    setErrorMsg('');
  };

  // Form
  const handleChangeInputForm = (property, value) => {
    value === '' ? setHasErrorInForm(true) : setHasErrorInForm(false);

    const newData = { ...partidoData };

    if(value !== ''){
    switch (property) {
      case 'jugadorLocal':
        newData.jugadorLocal = listaJugadores.filter((x) => x.id === parseInt(value))[0];
        break;
      case 'jugadorVisitante':
        newData.jugadorVisitante = listaJugadores.filter((x) => x.id === parseInt(value))[0];
        break;
      case 'fechaComienzo':
        newData.fechaComienzo = value;
        break;
      default:
        break;
      case 'cancha':
        newData.cancha = listaCanchas.filter((x) => x.id === parseInt(value))[0];
        break;
    }
  }
    setPartidoData(newData);
  };

  const handleSubmitForm = (e, form, isEdit) => {
    e.preventDefault();
    setHasErrorInForm(true);

    if (!validatePartido()) return;

    if (form.checkValidity()) isEdit ? editarPartido(partidoData.id) : agregarPartido();
  };

  // API
  const agregarPartido = async () => {
    try {
      console.log(partidoData);
      const data = { ...partidoData }
      data.fechaComienzo = stringToDate(data.fechaComienzo);
      await httpClient.post(`/partidos`, { data: data });
      await getPartidos();
    } catch (error) {
      console.log(error);
    }
    handleCloseModal();
  };

  const editarPartido = async (id) => {

    try {
      await httpClient.put(`/partidos/${id}`, { data: partidoData });
      await getPartidos();
    } catch (error) {
      console.log(error);
    }
    handleCloseModal();
  };

  const getPartidos = async () => {
    try {
      const data = await httpClient.get('/partidos');
      data.map(partido => {partido.fechaComienzo =new Date (partido.fechaComienzo).toLocaleDateString('es-AR', dateOptions);
    
    return partido;
    })
      setPartidosList(data);
    } catch (err) {
      console.log(err);
    }
  };

  const getJugadores = async () => {
    try {
      const data = await httpClient.get('/jugadores');
      setJugadoresList(data);
    } catch (err) {
      console.log(err);
    }
  };

  const getCanchas = async () => {

    try {
      const data = await httpClient.get('/canchas');
      setCanchasList(data);
    } catch (err) {
      console.log(err);
    }
  };

  const borrarPartido = async (id) => {
    try {
      await httpClient.delete(`/partidos/${id}`);
      setPartidosList(partidosList.filter((partido) => partido.id !== id));
    } catch (error) {
      console.log(error);
    }
  };

  const iniciarPartido = async(id) => {
    const partido = partidosList.filter(element => element.id === id);
    if(partido[0].estado === 'NO_INICIADO'){
      try{
        await httpClient.put(`/partidos/${id}/actions/init`);
      }
      catch(error){
        console.log(error);
      }
    } 
  }
  
  return (
    <>
      <Typography id={'title-id'}>Partido</Typography>
      <div className='mb-2'>
        <Button variant="success" onClick={() => handleOpenModal()}>Agregar partido</Button>
      </div>
      <TablePartido
        dataForTable={partidosList}
        editPartido={handleEditPartido}
        deletePartido={(id, event) => handleDeletePartido(id, event)}
        iniciarPartido={iniciarPartido}
      />
      <PartidoModal
        show={openModal}
        onHide={handleCloseModal}
        isEdit={isEdit}
        handleChange={handleChangeInputForm}
        validated={hasErrorInForm}
        handleSubmit={handleSubmitForm}
        errorMsg={errorMsg}
        partido={partidoData}
        listaJugadores={listaJugadores}
        listaCanchas={listaCanchas}
      />
    </>
  );
};

export default Partido;