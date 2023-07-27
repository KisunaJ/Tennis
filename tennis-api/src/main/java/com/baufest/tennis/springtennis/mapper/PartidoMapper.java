package com.baufest.tennis.springtennis.mapper;


import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.model.Partido;

public interface PartidoMapper {

    PartidoDTO EntityToDTO(Partido entity);
    Partido DTOToEntity(PartidoDTO entity);

}
