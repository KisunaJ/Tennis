package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.EntrenadorDTO;
import com.baufest.tennis.springtennis.model.Entrenador;

public interface EntrenadorMapper {

    EntrenadorDTO EntityToDTO(Entrenador Entrenador);
    Entrenador DTOToEntity(EntrenadorDTO entrenadorDTO);


}
