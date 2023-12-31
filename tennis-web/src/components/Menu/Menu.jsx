import React from "react";
import { Navbar, Nav} from "react-bootstrap";
import { Link } from "react-router-dom";

const Menu = () => {

    return(
        <Navbar collapseOnSelect expand="md" bg="dark" variant="dark">
            <div className="container">
            <Navbar.Brand>Baufest Blog</Navbar.Brand>
            <Navbar.Toggle aria-controls="responsive-navbar-nav" />
            <Navbar.Collapse id="responsive-navbar-nav">
                <Nav className="mr-auto">
                <Nav.Link as={Link} to="/">Home</Nav.Link>
                <Nav.Link as={Link} to="/partido">Partido</Nav.Link>
                <Nav.Link as={Link} to="/jugador">Jugador</Nav.Link>
                <Nav.Link as={Link} to="/entrenador">Entrenador</Nav.Link>
                <Nav.Link as={Link} to="/cancha">Cancha</Nav.Link>
                <Nav.Link as={Link} to="/About">About</Nav.Link>
                
                </Nav>
            </Navbar.Collapse>
            </div>
        </Navbar>
    )
}


export default Menu;
