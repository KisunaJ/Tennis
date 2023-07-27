package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.EntrenadorDTO;
import com.baufest.tennis.springtennis.model.Entrenador;
import org.springframework.stereotype.Component;

@Component
public class EntrenadorMapperImpl implements EntrenadorMapper {


    @Override
    public EntrenadorDTO EntityToDTO(Entrenador entrenador) {
        if (entrenador == null) {
            return null;
        }

        EntrenadorDTO entrenadorDTO = new EntrenadorDTO();

        entrenadorDTO.setId(entrenador.getId());
        entrenadorDTO.setNombre(entrenador.getNombre());


        return entrenadorDTO;

    }

    @Override
    public Entrenador DTOToEntity(EntrenadorDTO entrenadorDTO) {
        if (entrenadorDTO == null) {
            return null;
        }

        Entrenador entrenadorEntity = new Entrenador();

        entrenadorEntity.setId(entrenadorDTO.getId());
        entrenadorEntity.setNombre(entrenadorDTO.getNombre());


        return entrenadorEntity;

    }
}