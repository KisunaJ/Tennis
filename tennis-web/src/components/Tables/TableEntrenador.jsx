import React from "react";
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

const TableBody = (props) => {
    return props.listaEntrenadores.map(itemRow => {
        return (
            <tr key={`tr-${itemRow.id}`}>
                <td>{itemRow.id}</td>
                <td>{itemRow.nombre}</td>
                <td>
                    <Button onClick={event => props.editar(itemRow, event)} variant="primary">Editar</Button>{' '}
                    <Button onClick={event => props.borrar(itemRow.id, event)} variant="danger">Eliminar</Button>
                </td>
            </tr>
        )
    })
}


const TableEntrenador = (props) => {
    return (
        <>
            <Table striped bordered hover responsive="sm">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nombre</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {TableBody(props)}
                </tbody>
            </Table>
        </>
    )
}

export default TableEntrenador;