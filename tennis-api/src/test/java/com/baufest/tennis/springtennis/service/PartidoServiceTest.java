package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PartidoServiceTest {


    @InjectMocks
    private PartidoServiceImpl partidoService;

    @Mock
    private PartidoRepository partidoRepository;
    @Mock
    private PartidoMapper partidoMapper;

    private PartidoDTO partidoDto;
    private  Partido partido;
    Optional<Partido> partidoOptional;

    @BeforeEach
    void init() {
        partidoDto = new PartidoDTO();
        partido =new Partido();
        partidoOptional = Optional.ofNullable(partido);

    }

    @Test
    void testTraerJugadorPorID() {

        Long id = 1L;
        partidoDto.setId(id);
        partido.setId(id);
        //Mockeamos las precondiciones
        when(partidoRepository.findById(id)).thenReturn(partidoOptional);
        when(partidoMapper.EntityToDTO((any(Partido.class)))).thenReturn(partidoDto);
        //Llamamos al metodo a testear

        PartidoDTO partidosDto = partidoService.getById(id);
        //Asserts!
        assertEquals(partido.getId(), partidosDto.getId());
        verify(partidoRepository,times(1)).findById(id);
        verify(partidoMapper,times(1)).EntityToDTO(any(Partido.class));
    }

    @Test
    void testDeGuardarJugador() {

        Long id = 1L;
        Partido partidoRetorno = new Partido();
        partidoRetorno.setId(id);
        //Mockeamos las precondiciones
        when(partidoRepository.save(partido)).thenReturn(partidoRetorno);
        when(partidoMapper.EntityToDTO(partidoRetorno))
                .thenReturn(partidoDto);
        //Llamamos al metodo a testear

        PartidoDTO result = partidoService.save(partidoDto);
        //Asserts!
        assertEquals(partidoRetorno.getId(), result.getId());
        verify(partidoRepository,times(1)).save(partido);
        verify(partidoMapper,times(1)).EntityToDTO(any(Partido.class));
    }



}
