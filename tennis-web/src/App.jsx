import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import Wrapper from "./components/Wrapper/Wrapper";
import About from "./containers/About/About";
import Home from "./containers/Home/Home"
import Jugador from "./containers/Jugador/Jugador"
import Partido from "./containers/Partido/Partido"
import NotFound from "./containers/NotFound/NotFound"
import Detalles from "./containers/Jugador/Detalles"
import JugarPartido from "./containers/JugarPartido/JugarPartido"
import Entrenador from "./containers/Entrenador/Entrenador"
import Cancha from "./containers/Cancha/Cancha";


const App = () => {

    return(
        <BrowserRouter>
       <Wrapper>
            <Switch>
                <Route exact path="/" component={Home}/>
                <Route exact path="/jugador" component={Jugador}/>
                <Route exact path="/jugador/detalle/:id" component={Detalles}/>
                <Route exact path="/partido" component={Partido}/>
                <Route exact path="/partido/jugar-partido" component={JugarPartido} />
                <Route exact path="/entrenador" component={Entrenador}/>
                <Route exact path="/cancha" component={Cancha}/>
                <Route exact path="/about" component={About}/>
                <Route component={NotFound}/>
            </Switch>
       </Wrapper>
       </BrowserRouter>
    )
};

export default App;


