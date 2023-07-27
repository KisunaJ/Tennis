package com.baufest.tennis.springtennis.service;


import com.baufest.tennis.springtennis.dto.EntrenadorDTO;
import com.baufest.tennis.springtennis.mapper.EntrenadorMapper;
import com.baufest.tennis.springtennis.model.Entrenador;
import com.baufest.tennis.springtennis.repository.EntrenadorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EntrenadorServiceImpl implements EntrenadorService {

    private final EntrenadorMapper entrenadorMapper;
    private final EntrenadorRepository entrenadorRepository;

    public EntrenadorServiceImpl(EntrenadorMapper entrenadorMapper, EntrenadorRepository entrenadorRepository) {
        this.entrenadorMapper = entrenadorMapper;
        this.entrenadorRepository = entrenadorRepository;
    }

    @Override
    public List<EntrenadorDTO> listAll() {

        List<EntrenadorDTO> listaDto = new ArrayList<>();
        List<Entrenador> listaEntity = entrenadorRepository.findAll();

        for (Entrenador tempEntity: listaEntity) {

            listaDto.add(entrenadorMapper.EntityToDTO(tempEntity));
        }
        return listaDto;
    }

    @Override
    public EntrenadorDTO getById(Long id) {

        Optional<Entrenador> entrenador = entrenadorRepository.findById(id);
        if (entrenador.isPresent()){

            return entrenadorMapper.EntityToDTO(entrenador.get());

        }else{

            throw new NoSuchElementException("El entrenador con ese id no existe");
        }
    }

    @Override
    public EntrenadorDTO save(EntrenadorDTO entrenador) {

        if (entrenador.getId() != null && entrenadorRepository.existsById(entrenador.getId())) {
            throw new IllegalArgumentException("El entrenador con ese id ya existe");
        }
       return entrenadorMapper.EntityToDTO(entrenadorRepository.save(entrenadorMapper.DTOToEntity(entrenador)));

    }

    @Override
    public EntrenadorDTO update(EntrenadorDTO entrenador) {

        if (entrenadorRepository.existsById(entrenador.getId())) {

            Entrenador tempEntity = entrenadorRepository.save(entrenadorMapper.DTOToEntity(entrenador));

            return entrenadorMapper.EntityToDTO(tempEntity);

        }else{

            throw new NoSuchElementException("No se encontró el entrenador con ese id");
        }


    }

    @Override
    public void delete(Long id) {

        if (entrenadorRepository.existsById(id)){

            entrenadorRepository.deleteById(id);
        }else{
            throw new NoSuchElementException("El entrenador con ese id no existe");
        }

    }
}
