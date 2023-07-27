package com.baufest.tennis.springtennis.service;


import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.mapper.CanchaMapper;
import com.baufest.tennis.springtennis.mapper.JugadorMapper;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Cancha;
import com.baufest.tennis.springtennis.model.Jugador;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.CanchaRepository;
import com.baufest.tennis.springtennis.repository.JugadorRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CanchaServiceTest {

    CanchaServiceImpl canchaService;

    @Mock
    CanchaRepository canchaRepository;
    @Mock
    CanchaMapper canchaMapper;
    @Mock
    PartidoRepository partidoRepository;

    private final List<Cancha> listaCanchasEntity = new ArrayList<>();
    private final List<CanchaDTO> listaCanchasDto = new ArrayList<>();
    private final List<Partido> listaPartidoEntity = new ArrayList<>();

    Cancha cancha1;
    Cancha cancha2;
    Cancha cancha3;
    CanchaDTO cancha4;
    CanchaDTO cancha5;
    CanchaDTO cancha6;

    @BeforeEach
    public void setUp() {
        canchaService = new CanchaServiceImpl(canchaRepository, canchaMapper, partidoRepository);

        cancha1 = new Cancha(1L, "cancha1", "Por ahii");
        cancha2 = new Cancha(2L, "cancha2", "Por ahii");
        cancha3 = new Cancha(3L, "cancha3", "Por ahii");
        listaCanchasEntity.add(cancha1);
        listaCanchasEntity.add(cancha2);
        listaCanchasEntity.add(cancha3);

        cancha4 = new CanchaDTO(1L, "cancha1", "Por ahii", 0);
        cancha5 = new CanchaDTO(2L, "cancha2", "Por ahii", 0);
        cancha6 = new CanchaDTO(3L, "cancha3", "Por ahii", 0);
        listaCanchasDto.add(cancha4);
        listaCanchasDto.add(cancha5);
        listaCanchasDto.add(cancha6);

        Partido partido1 = new Partido(1L, new Date(2022, 7, 7, 15, 00), Estado.FINALIZADO, null, null, 1, 0, "ADV", "15", 1, 2, cancha1);
        Partido partido2 = new Partido(2L, new Date(2022, 7, 7, 5, 00), Estado.NO_INICIADO, null, null, 1, 0, "ADV", "15", 1, 2, cancha2);
        Partido partido3 = new Partido(3L, new Date(2022, 7, 8, 13, 45), Estado.FINALIZADO, null, null, 1, 0, "ADV", "15", 1, 2, cancha1);
        listaPartidoEntity.add(partido1);
        listaPartidoEntity.add(partido2);
        listaPartidoEntity.add(partido3);
    }

    @Test
    void test() {
        //Arrange
        when(canchaRepository.findAll()).thenReturn(listaCanchasEntity);
        when(canchaMapper.EntityToDTO(cancha1)).thenReturn(cancha4);
        when(canchaMapper.EntityToDTO(cancha2)).thenReturn(cancha5);
        when(canchaMapper.EntityToDTO(cancha3)).thenReturn(cancha6);
        when(partidoRepository.findAll()).thenReturn(listaPartidoEntity);

        //Act
        List<CanchaDTO> result = canchaService.listAll();

        //Assert
        assertEquals(listaCanchasDto.size(), result.size());
        verify(canchaRepository,times(1)).findAll();
    }
}
