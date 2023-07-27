import React, { useEffect, useState } from "react";
import EntrenadorModal from "../../components/Modals/EntrenadorModal";
import TableEntrenador from "../../components/Tables/TableEntrenador";
import Typography from "../../components/Typography/Typography";
import httpClient from "../../lib/httpClient";
import {Button} from "react-bootstrap";



let entrenadorInit = {
    id: 0,
    nombre: '',
}

const Entrenador = (props) => {
    const [entrenadoresList, setEntrenadoresList] = useState([]);
    const [entrenadorData, setEntrenadorData] = useState(entrenadorInit);
    const [esEditar, setEsEditar] = useState(false);
    const [tieneErrorEnElForm, setTieneErrorEnElForm] = useState(false);
    const [mostrarModal, setMostrarModal] = useState(false);
    const [errorMsg, setErrorMsg] = useState('');

    useEffect(() => {
        traerEntrenadores()
    }, []);

    //Handles
    //Modal
    const handleOpenModal = (esEditar = false, entrenadorAEditar = null) => {
        setEsEditar(esEditar);

        if (esEditar) {
            setEntrenadorData(entrenadorAEditar)
        }

        setMostrarModal(true);
    }

    const handleCloseModal = () => {
        setMostrarModal(false);
        setEsEditar(false);
        setTieneErrorEnElForm(false);
        setEntrenadorData(entrenadorInit);
        setErrorMsg('');
    }

    //Botones
    const handleEdit = (entrenadorAEditar, event) => {
        event.preventDefault();
        handleOpenModal(true, entrenadorAEditar)
    }

    const handleDelete = (id, event) => {
        event.preventDefault();
        if (window.confirm('Estas seguro?')) {
            borrarEntrenador(id);
        }
    };

    // Form
    const handleChangeInputForm = (property, value) => {
        // Si el valor del input es vacío, entonces setea que hay un error
        (value === '') ? setTieneErrorEnElForm(true) : setTieneErrorEnElForm(false);

        setEntrenadorData({...entrenadorData, [property]: value});
    }

    const handleSubmitForm = (e, form, esEditar) => {
        e.preventDefault();
        setTieneErrorEnElForm(true);

        if (form.checkValidity())
            esEditar ? editarEntrenador(entrenadorData.id) : agregarEntrenador()
    }

    //Funciones
    const traerEntrenadores = async () => {

        try {
            const data = await httpClient.get('/entrenadores');
            setEntrenadoresList(data);
        } catch (err) {
            console.log(err);
        }
    };

    const agregarEntrenador = async () => {
        try {

          await httpClient.post(`/entrenadores`, { data: entrenadorData });
          await traerEntrenadores();
        } catch (error) {
          console.log(error);
        }
        handleCloseModal();
      };

    const editarEntrenador = async (id) => {
        try {
            await httpClient.put(`/entrenadores/${id}`, { data: entrenadorData });
            await traerEntrenadores();
        } catch (error) {
        console.log(error);
        }
        handleCloseModal();
    };

    const borrarEntrenador = async (id) => {
        try {
            await httpClient.delete(`/entrenadores/${id}`);
            setEntrenadoresList(entrenadoresList.filter((entrenador) => entrenador.id !== id));
        } catch (error) {
            console.log(error);
        }
    };


    return (
        <>
            <Typography id={"title-id"} colores="rosado">Entrenador</Typography>.
            <div className='mb-2'>
                <Button variant="success" onClick={() => handleOpenModal()}>Agregar entrenador</Button>
            </div>

            <TableEntrenador
                listaEntrenadores={entrenadoresList}
                editar={handleEdit}
                borrar={(id, event) => handleDelete(id, event)}
            />

            <EntrenadorModal
                mostrar={mostrarModal}
                alOcultar={handleCloseModal}
                esEditar={esEditar}
                handleChange={handleChangeInputForm}
                entrenador={entrenadorData}
                validado={tieneErrorEnElForm}
                handleSubmit={handleSubmitForm}
                errorMsg={errorMsg}
            />
        </>
    )
}

export default Entrenador;