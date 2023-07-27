package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.model.Cancha;

public interface CanchaMapper {

    CanchaDTO EntityToDTO(Cancha cancha);
    Cancha DTOToEntity(CanchaDTO canchaDTO);
}
