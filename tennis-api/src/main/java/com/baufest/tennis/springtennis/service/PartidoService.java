package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.ModoJugador;

import java.util.List;

public interface PartidoService {



    List<PartidoDTO> listAll();
    PartidoDTO getById(Long id);
    PartidoDTO save(PartidoDTO partido);
    PartidoDTO update(PartidoDTO partido);
    void delete(Long id);
    void initGame(Long id);
    PartidoDTO sumarPuntos(Long id, ModoJugador modo);
}
