import React, {useEffect, useState} from "react";
import Typography from "../../components/Typography/Typography";
import {Button} from "react-bootstrap";
import TableJugadores from '../../components/Tables/TableJugadores';
import JugadorModal from "../../components/Modals/JugadorModal";
import httpClient from "../../lib/httpClient";



let jugadorInit = {
    nombre: '',
    puntos: 0,
    entrenador: {
      id: -1,
      nombre: '',
    },
}

const Jugador = (props) => {
    const [jugadoresList, setJugadoresList] = useState([]);
    const [entrenadoresList, setEntrenadoresList] = useState([]);
    const [jugadorData, setJugadorData] = useState(jugadorInit);
    const [isEdit, setIsEdit] = useState(false);
    const [hasErrorInForm, setHasErrorInForm] = useState(false);
    const [openModal, setOpenModal] = useState(false);
    const [errorMsg,setErrorMsg] = useState('');

    useEffect( () => {
         getJugadores()
         traerEntrenadores()
    }, []);

    // Buttons
    const handleDetail = (data, event) => {
        event.preventDefault();
        props.history.push(`/jugador/detalle/${data.id}`, {data});
    };

    const handleEdit = (editData, event) => {
        event.preventDefault();
        handleOpenModal(true, editData)
    }
    const handleDelete = (id, event) => {
        event.preventDefault();
        if (window.confirm('Estas seguro?')) {
          borrarJugador(id);
        }
      };

      const handleRanking = (id, event) => {
        event.preventDefault();
        calcularRanking(id);
    }
    

    // Modal
    const handleOpenModal = (editarJugador = false, jugadorToEdit = null) => {
        setIsEdit(editarJugador);

        if (editarJugador) {
            setJugadorData(jugadorToEdit)
        }

        setOpenModal(true);
    }

    const handleCloseModal = () => {
        setOpenModal(false);
        setIsEdit(false);
        setHasErrorInForm(false);
        setJugadorData(jugadorInit);
        setErrorMsg('');
    }

    // Form
    const handleChangeInputForm = (property, value) => {
        // Si el valor del input es vacío, entonces setea que hay un error
        (value === '') ? setHasErrorInForm(true) : setHasErrorInForm(false);

        const newData = {...jugadorData, [property]: value};

        if(property == 'entrenador') {
            newData.entrenador = entrenadoresList.filter((x) => x.id === parseInt(value))[0];
        }

        setJugadorData(newData);
    }

    const handleSubmitForm = (e, form, isEdit) => {
        e.preventDefault();
        setHasErrorInForm(true);

        if (form.checkValidity())
            isEdit ? editarJugador(jugadorData.id) : agregarJugador()
    }

    // API
    // const agregarJugador = () => {
    //     let model = {
    //         ...jugadorData,
    //         id: jugadoresList.length + 1,
    //     }
    //     jugadoresList.push(model);
    //     setJugadoresList(jugadoresList);
    //     handleCloseModal()
    // };

    const agregarJugador = async () => {
        try {
          const data ={...jugadorData}
          data.entrenador = data.entrenador.id === -1 ? null : data.entrenador;

          await httpClient.post(`/jugadores`, { data: data });
          await getJugadores();
          //setJugadoresList([...jugadoresList, jugadorData]);
          
        } catch (error) {
          console.log(error);
        }
        handleCloseModal();
      };
    
      const getJugadores = async () =>{

        try{
        const data = await httpClient.get('/jugadores');
        setJugadoresList(data);
        }catch (err){
            console.log(err);
        }

    };

    const editarJugador = async (id) => {
        try {
          await httpClient.put(`/jugadores/${id}`, { data: jugadorData });
          await getJugadores();
        } catch (error) {
          console.log(error);
        }
        handleCloseModal();
      };
    
      const borrarJugador = async (id) => {
        try {
          await httpClient.delete(`/jugadores/${id}`);
          setJugadoresList(jugadoresList.filter((jugador) => jugador.id !== id));
        } catch (error) {
          console.log(error);
        }
      };

      const calcularRanking = async (id) => {
        try {
          await httpClient.put(`/jugadores/calcularRanking/${id}`);
          getJugadores();
        } catch (error) {
          console.log(error);
        }
      };
    
      const traerEntrenadores = async () => {

        try {
            const data = await httpClient.get('/entrenadores');
            setEntrenadoresList(data);
        } catch (err) {
            console.log(err);
        }
    };
    
    return (
        <>
            <Typography id={"title-id"}>Jugador</Typography>
            <div className='mb-2'>
                <Button variant="success" onClick={() => handleOpenModal()}>Agregar jugador</Button>
            </div>
            <TableJugadores
                dataForTable={jugadoresList}
                detail={handleDetail}
                edit={handleEdit}
                delete={(id, event) => handleDelete(id, event)}
                ranking={(id, event) => handleRanking(id, event)}
            />
            <JugadorModal
                show={openModal}
                onHide={handleCloseModal}
                isEdit={isEdit}
                handleChange={handleChangeInputForm}
                jugador={jugadorData}
                listaEntrenadores={entrenadoresList}
                validated={hasErrorInForm}
                handleSubmit={handleSubmitForm}
                errorMsg={errorMsg}

            />
        </>
    )
}

export default Jugador;
