package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.JugadorRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JugadorServiceImpl implements JugadorService {

    public static final String PLAYER_WITH_ID = "Player with id = ";
    public static final String DOES_NOT_EXIST = " does not exist.";
    public static final String ALREADY_EXISTS = " already exists.";

    private final JugadorRepository jugadorRepository;
    private final JugadorMapper jugadorMapper;
    private final PartidoRepository partidoRepository;
    private final PartidoMapper partidoMapper;

    @Autowired
    public JugadorServiceImpl(JugadorRepository jugadorRepository,
                              JugadorMapper jugadorMapper, PartidoRepository partidoRepository, PartidoMapper partidoMapper) {
        this.jugadorRepository = jugadorRepository;
        this.jugadorMapper = jugadorMapper;
        this.partidoRepository = partidoRepository;
        this.partidoMapper = partidoMapper;
    }

    @Override
    public List<JugadorDTO> listAll() {

        return jugadorRepository.findAllByOrderByNombreAsc().stream()
                .map(this.jugadorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JugadorDTO getById(Long id) {
        return jugadorRepository.findById(id).map(this.jugadorMapper::toDTO)
                .orElseThrow(() -> new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST));
    }


    @Override
    public JugadorDTO save(JugadorDTO jugador) {
        boolean exists = jugador.getId() != null && jugadorRepository.existsById(jugador.getId());
        if (exists) {
            throw new IllegalArgumentException(PLAYER_WITH_ID + jugador.getId() + ALREADY_EXISTS);
        }
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador)));
    }


    @Override
    public JugadorDTO update(JugadorDTO jugador) {
        boolean exists = jugadorRepository.existsById(jugador.getId());
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + jugador.getId() + DOES_NOT_EXIST);
        }
        return this.jugadorMapper.toDTO(jugadorRepository.save(this.jugadorMapper.fromDTO(jugador)));
    }

    @Override
    public void delete(Long id) {
        boolean exists = jugadorRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }
        jugadorRepository.deleteById(id);
    }

    @Override
    public JugadorDTO calcularRanking(Long id) {

        boolean exists = jugadorRepository.existsById(id);
        if (!exists) {
            throw new NoSuchElementException(PLAYER_WITH_ID + id + DOES_NOT_EXIST);
        }

        Optional<Jugador> jugadorEntity = jugadorRepository.findById(id);
        JugadorDTO jugadorDTO = new JugadorDTO();
        if (jugadorEntity.isPresent()) {

            jugadorDTO = jugadorMapper.toDTO(jugadorEntity.get());
        }
        int puntos = 0;
        List<Partido> partidoEntity = partidoRepository.findAll().stream()
                .filter(x -> x.getEstado() == Estado.FINALIZADO)
                .filter(x -> x.getJugadorLocal().getId() == id || x.getJugadorVisitante().getId() == id).collect(Collectors.toList());

        for (Partido temp : partidoEntity) {
            // PARA SABER SI ES LOCAL O VISITANTE
            if (temp.getJugadorLocal().getId().equals(id)) {
                //SI GANA COMO LOCAL
                if (temp.getCantidadGamesLocal() > temp.getCantidadGamesVisitante()) {

                    puntos += 10;
                }
                // SI PIERDE COMO LOCAL
                else {
                    puntos -= 5;

                    //SI NO TIENE PUNTOS PARA QUE NO DE NEGATIVO Y QUEDE EN 0
                    if (puntos < 0) {

                        puntos = 0;
                    }
                }
            }
            // SI ES VISITANTE
            else if (temp.getJugadorVisitante().getId().equals(id)) {
                //SI GANA VISITANTE
                if (temp.getCantidadGamesVisitante() > temp.getCantidadGamesLocal()) {

                    puntos += 15;
                }
            }
        }
        jugadorDTO.setPuntos(puntos);
        return update(jugadorDTO);
    }
}