package com.baufest.tennis.springtennis.mapper;

import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.model.Cancha;
import org.springframework.stereotype.Component;

@Component
public class CanchaMapperImpl implements CanchaMapper {


    @Override
    public CanchaDTO EntityToDTO(Cancha cancha) {

        CanchaDTO canchaDTO = new CanchaDTO();

        canchaDTO.setId(cancha.getId()) ;
        canchaDTO.setNombre( cancha.getNombre() );
        canchaDTO.setDireccion( cancha.getDireccion() );


        return canchaDTO;
    }

    @Override
    public Cancha DTOToEntity(CanchaDTO canchaDTO) {

        Cancha cancha = new Cancha();

        cancha.setId(canchaDTO.getId());
        cancha.setNombre(canchaDTO.getNombre());
        cancha.setDireccion(canchaDTO.getDireccion());

        return cancha;
    }
}
