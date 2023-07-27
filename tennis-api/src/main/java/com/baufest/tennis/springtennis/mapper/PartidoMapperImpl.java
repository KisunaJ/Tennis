package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.model.Partido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartidoMapperImpl implements PartidoMapper {


    private final JugadorMapper jugadorMapper;
    private final  CanchaMapper canchaMapper;

    @Autowired
    public PartidoMapperImpl(JugadorMapper jugadorMapper, CanchaMapper canchaMapper) {
        this.jugadorMapper = jugadorMapper;
        this.canchaMapper = canchaMapper;
    }


    @Override
    public PartidoDTO EntityToDTO(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDTO partidoDTO = new PartidoDTO();

        partidoDTO.setId( partido.getId() );
        partidoDTO.setFechaComienzo( partido.getFechaComienzo() );
        partidoDTO.setEstado( partido.getEstado() );
        partidoDTO.setJugadorLocal( jugadorMapper.toDTO( partido.getJugadorLocal() ) );
        partidoDTO.setJugadorVisitante( jugadorMapper.toDTO( partido.getJugadorVisitante() ) );
        partidoDTO.setScoreLocal( partido.getScoreLocal() );
        partidoDTO.setPuntosGameActualLocal( partido.getPuntosGameActualLocal() );
        partidoDTO.setCantidadGamesLocal( partido.getCantidadGamesLocal() );
        partidoDTO.setScoreVisitante( partido.getScoreVisitante() );
        partidoDTO.setPuntosGameActualVisitante( partido.getPuntosGameActualVisitante() );
        partidoDTO.setCantidadGamesVisitante( partido.getCantidadGamesVisitante() );
        partidoDTO.setCancha(canchaMapper.EntityToDTO(partido.getCancha()));

        return partidoDTO;
    }

    @Override
    public Partido DTOToEntity(PartidoDTO partidoDTO) {
        if ( partidoDTO == null ) {
            return null;
        }

        Partido partido = new Partido();

        partido.setId( partidoDTO.getId() );
        partido.setFechaComienzo( partidoDTO.getFechaComienzo() );
        partido.setEstado( partidoDTO.getEstado() );
        partido.setJugadorLocal( jugadorMapper.fromDTO( partidoDTO.getJugadorLocal() ) );
        partido.setJugadorVisitante( jugadorMapper.fromDTO( partidoDTO.getJugadorVisitante() ) );
        partido.setScoreLocal( partidoDTO.getScoreLocal() );
        partido.setPuntosGameActualLocal( partidoDTO.getPuntosGameActualLocal() );
        partido.setCantidadGamesLocal( partidoDTO.getCantidadGamesLocal() );
        partido.setScoreVisitante( partidoDTO.getScoreVisitante() );
        partido.setPuntosGameActualVisitante( partidoDTO.getPuntosGameActualVisitante() );
        partido.setCantidadGamesVisitante( partidoDTO.getCantidadGamesVisitante() );
        partido.setCancha(canchaMapper.DTOToEntity(partidoDTO.getCancha()));

        return partido;
    }
}