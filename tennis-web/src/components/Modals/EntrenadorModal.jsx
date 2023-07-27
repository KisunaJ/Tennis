import React, {useRef} from 'react';
import {Form, Button, Modal} from 'react-bootstrap';
import FormRowInput from "../FormRow/FormRowInput";

const EntrenadorModal = (props) => {
    const formRef = useRef(null);
    const { mostrar, alOcultar, esEditar, handleChange, entrenador, validado, handleSubmit, errorMsg } = props;

    return (
        <Modal
            show={mostrar}
            onHide={alOcultar}
            centered={true} 
            backdrop="static" 
            keyboard={false} 
        >
            <Modal.Header closeButton>
                <Modal.Title>{ esEditar ? 'Editar': 'Agregar' } Entrenador</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <Form className={"form"} noValidate validated={validado} ref={formRef}>
                    <FormRowInput
                        label={'Nombre'}
                        type={'text'}
                        required={true}
                        placeholder={'Nombre del entrenador'}
                        property={'nombre'}
                        value={entrenador.nombre}
                        handleChange={handleChange}
                    />

                    {errorMsg !== '' &&
                        <Form.Group className="alert-danger">
                            {errorMsg}
                        </Form.Group>
                    }
                </Form>
            </Modal.Body>

            <Modal.Footer>
                <Button onClick={alOcultar} variant="danger">Cancelar</Button>
                <Button onClick={(e)=> handleSubmit(e,formRef.current, esEditar)} variant="success">{ esEditar ? 'Editar': 'Agregar' }</Button>
            </Modal.Footer>
        </Modal>
    )
}

export default EntrenadorModal;