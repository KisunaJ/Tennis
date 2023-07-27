import React from 'react';
import Table from 'react-bootstrap/Table';
import Button from 'react-bootstrap/Button';

const TableBody = (data) => {
    return data.dataForTable.map(itemRow => {
        return (
            <tr key={`tr-${itemRow.id}`}>
                <td>{itemRow.id}</td>
                <td>{itemRow.nombre}</td>
                <td>{itemRow.puntos}</td>
                <td>{itemRow.entrenador ? itemRow.entrenador.nombre : 'No tiene'}</td>
                <td>
                
                    <Button onClick={event => data.edit(itemRow, event)} variant="primary">Editar</Button>{' '}
                    <Button onClick={event => data.delete(itemRow.id, event)} variant="danger">Eliminar</Button>
                    <Button onClick={event => data.ranking(itemRow.id, event)} variant="success">Ranking Jugador</Button>

                </td>
            </tr>
        )
    })
}

const TableJugadores = (props) => {
    return (
        <Table striped bordered hover responsive="sm">
            <thead>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Puntos</th>
                <th>Entrenador</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            {TableBody(props)}
            </tbody>
        </Table>
    )
}

export default TableJugadores;