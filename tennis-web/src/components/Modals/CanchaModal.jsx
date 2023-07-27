import React, {useRef} from 'react';
import {Form, Button, Modal} from 'react-bootstrap';
import FormRowInput from "../FormRow/FormRowInput";

const CanchaModal = (props) => {
    const formRef = useRef(null);
    const { mostrar, alOcultar, esEditar, handleChange, cancha, validado, handleSubmit, errorMsg } = props;

    return (
        <Modal
            show={mostrar}
            onHide={alOcultar}
            centered={true}
            backdrop="static" 
            keyboard={false}  
        >
            <Modal.Header closeButton>
                <Modal.Title>{ esEditar ? 'Editar': 'Agregar' } Cancha</Modal.Title>
            </Modal.Header>

            <Modal.Body>
                <Form className={"form"} noValidate validated={validado} ref={formRef}>
                    <FormRowInput
                        label={'Nombre'}
                        type={'text'}
                        required={true}
                        placeholder={'Nombre de la cancha'}
                        property={'nombre'}
                        value={cancha.nombre}
                        handleChange={handleChange}
                    />
                    <FormRowInput
                        label={'Direccion'}
                        type={'text'}
                        required={true}
                        placeholder={'Direccion de la cancha'}
                        property={'direccion'}
                        value={cancha.direccion}
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

export default CanchaModal;