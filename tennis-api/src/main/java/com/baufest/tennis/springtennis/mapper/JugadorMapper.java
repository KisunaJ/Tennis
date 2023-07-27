package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.model.Jugador;



public interface JugadorMapper {


    JugadorDTO toDTO(Jugador entity);
    Jugador fromDTO(JugadorDTO entity);

}
