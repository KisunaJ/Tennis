package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.model.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JugadorMapperImpl implements JugadorMapper {

    private final EntrenadorMapper entrenadorMapper;


    @Autowired
    public JugadorMapperImpl(EntrenadorMapper entrenadorMapper) {
        this.entrenadorMapper = entrenadorMapper;
    }

    @Override
    public JugadorDTO toDTO(Jugador entity) {
        if ( entity == null ) {
            return null;
        }

        JugadorDTO jugadorDTO = new JugadorDTO();

        jugadorDTO.setId( entity.getId() );
        jugadorDTO.setNombre( entity.getNombre() );
        jugadorDTO.setPuntos( entity.getPuntos() );
        jugadorDTO.setEntrenador(entrenadorMapper.EntityToDTO(entity.getEntrenador()));

        return jugadorDTO;
    }


    @Override
    public Jugador fromDTO(JugadorDTO entity) {
        if ( entity == null ) {
            return null;
        }

        Jugador jugador = new Jugador();

        jugador.setId( entity.getId() );
        jugador.setNombre( entity.getNombre() );
        jugador.setPuntos( entity.getPuntos() );
        jugador.setEntrenador(entrenadorMapper.DTOToEntity(entity.getEntrenador()));

        return jugador;
    }
}

