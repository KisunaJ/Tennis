package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.mapper.CanchaMapper;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Cancha;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.CanchaRepository;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CanchaServiceImpl implements CanchaService{

    private final CanchaRepository canchaRepository;
    private final CanchaMapper canchaMapper;
    private final PartidoRepository partidoRepository;

    @Autowired
    public CanchaServiceImpl(CanchaRepository canchaRepository, CanchaMapper canchaMapper, PartidoRepository partidoRepository) {
        this.canchaRepository = canchaRepository;
        this.canchaMapper = canchaMapper;
        this.partidoRepository = partidoRepository;
    }

    @Override
    public List<CanchaDTO> listAll() {

        List<CanchaDTO> listafinal = new ArrayList<>();
        List<Cancha> listaEntity = canchaRepository.findAll();
        for (Cancha temp:listaEntity) {
            CanchaDTO canchaDTO =canchaMapper.EntityToDTO(temp);
            canchaDTO.setCantidadPartidos(calcularCantPartidos(canchaDTO.getId()));
            listafinal.add(canchaDTO);
        }
        return listafinal;
    }

    @Override
    public CanchaDTO getById(Long id) {

        Optional<Cancha> canchaEntity  = canchaRepository.findById(id);
        if (canchaEntity.isPresent()){

            return  canchaMapper.EntityToDTO(canchaEntity.get());
        }else{
            throw new NoSuchElementException("No se encontro la cancha con ese id");
        }
    }

    @Override
    public CanchaDTO save( CanchaDTO cancha) {

        if (cancha.getId() != null && canchaRepository.existsById(cancha.getId())) {
            throw new IllegalArgumentException("La cancha con ese id ya existe");
        }
       Cancha canchaEntity= canchaRepository.save(canchaMapper.DTOToEntity(cancha));
       return canchaMapper.EntityToDTO(canchaEntity);
    }

    @Override
    public CanchaDTO update(CanchaDTO cancha) {

        if (canchaRepository.existsById(cancha.getId())) {
            Cancha canchaEntity = canchaRepository.save(canchaMapper.DTOToEntity(cancha));
            return canchaMapper.EntityToDTO(canchaEntity);
        } else {
            throw new NoSuchElementException("La cancha con ese id no existe");
        }
    }

    @Override
    public void delete(Long id) {

        if (canchaRepository.existsById(id)){

            canchaRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("La cancha con ese id no existe");
        }
    }

    private int calcularCantPartidos(Long id) {

        LocalDate hoy = LocalDate.now();

        List<Partido> partidoEntity = partidoRepository.findAll().stream()
                .filter(x -> x.getCancha().getId().equals(id))
                .filter(x -> x.getFechaComienzo().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().compareTo(hoy) == 0)
                .collect(Collectors.toList());


        return partidoEntity.size();
    }
}
