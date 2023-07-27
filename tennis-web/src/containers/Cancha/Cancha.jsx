import React, { useEffect, useState } from "react";
import CanchaModal from "../../components/Modals/CanchaModal";
import TableCancha from "../../components/Tables/TableCancha";
import Typography from "../../components/Typography/Typography";
import httpClient from "../../lib/httpClient";
import {Button} from "react-bootstrap";



let canchaInit = {
    id: 0,
    nombre: '',
    direccion: '',
    cantidadPartidos: 0,
}

const Cancha = (props) => {
    const [canchasList, setCanchasList] = useState([]);
    const [canchaData, setCanchaData] = useState(canchaInit);
    const [esEditar, setEsEditar] = useState(false);
    const [tieneErrorEnElForm, setTieneErrorEnElForm] = useState(false);
    const [mostrarModal, setMostrarModal] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    useEffect(() => {
        traerCanchas()
    }, []);

    //Handles
    //Modal
    const handleOpenModal = (esEditar = false, canchaAEditar = null) => {
        setEsEditar(esEditar);

        if (esEditar) {
            setCanchaData(canchaAEditar)
        }

        setMostrarModal(true);
    }

    const handleCloseModal = () => {
        setMostrarModal(false);
        setEsEditar(false);
        setTieneErrorEnElForm(false);
        setCanchaData(canchaInit);
        setErrorMsg('');
    }

    //Botones
    const handleEdit = (canchaAEditar, event) => {
        event.preventDefault();
        handleOpenModal(true, canchaAEditar)
    }

    const handleDelete = (id, event) => {
        event.preventDefault();
        if (window.confirm('Estas seguro?')) {
            borrarCancha(id);
        }
    };

    // Form
    const handleChangeInputForm = (property, value) => {
        
        (value === '') ? setTieneErrorEnElForm(true) : setTieneErrorEnElForm(false);

        setCanchaData({...canchaData, [property]: value});
    }

    const handleSubmitForm = (e, form, esEditar) => {
        e.preventDefault();
        setTieneErrorEnElForm(true);

        if (form.checkValidity())
            esEditar ? editarCancha(canchaData.id) : agregarCancha()
    }

    //Funciones
    const traerCanchas = async () => {

        try {
            const data = await httpClient.get('/canchas');
            setCanchasList(data);
        } catch (err) {
            console.log(err);
        }
    };

    const agregarCancha = async () => {
        try {

          await httpClient.post(`/canchas`, { data: canchaData });
          await traerCanchas();
        } catch (error) {
          console.log(error);
        }
        handleCloseModal();
      };

    const editarCancha = async (id) => {
        try {
            await httpClient.put(`/canchas/${id}`, { data: canchaData });
            await traerCanchas();
        } catch (error) {
        console.log(error);
        }
        handleCloseModal();
    };

    const borrarCancha = async (id) => {
        try {
            await httpClient.delete(`/canchas/${id}`);
            setCanchaList(canchasList.filter((cancha) => cancha.id !== id));
        } catch (error) {
            console.log(error);
        }
    };


    return (
        <>
            <Typography id={"title-id"} colores="rosado">Cancha</Typography>.
            <div className='mb-2'>
                <Button variant="success" onClick={() => handleOpenModal()}>Agregar cancha</Button>
            </div>

            <TableCancha
                listaCanchas={canchasList}
                editar={handleEdit}
                borrar={(id, event) => handleDelete(id, event)}
            />

            <CanchaModal
                mostrar={mostrarModal}
                alOcultar={handleCloseModal}
                esEditar={esEditar}
                handleChange={handleChangeInputForm}
                cancha={canchaData}
                validado={tieneErrorEnElForm}
                handleSubmit={handleSubmitForm}
                errorMsg={errorMsg}
            />
        </>
    )
}

export default Cancha;