import React from "react";
import { Title } from "./styles";
import { Title2 } from "./styles";

const Typography = (props) => {

    if(props.colores == "rosado"){
        return <Title2>{props.children}</Title2>
    }
    return <Title>{props.children}</Title>
}

export default Typography;
