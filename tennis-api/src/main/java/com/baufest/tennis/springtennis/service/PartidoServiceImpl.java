package com.baufest.tennis.springtennis.service;

import com.baufest.tennis.springtennis.dto.PartidoDTO;
import com.baufest.tennis.springtennis.enums.Estado;
import com.baufest.tennis.springtennis.enums.ModoJugador;
import com.baufest.tennis.springtennis.mapper.PartidoMapper;
import com.baufest.tennis.springtennis.model.Partido;
import com.baufest.tennis.springtennis.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    private final PartidoRepository partidoRepository;
    private final PartidoMapper partidoMapper;

    @Autowired
    public PartidoServiceImpl(PartidoRepository partidoRepository, PartidoMapper partidoMapper) {
        this.partidoRepository = partidoRepository;
        this.partidoMapper = partidoMapper;
    }


    @Override
    public List<PartidoDTO> listAll() {

        List<PartidoDTO> partidomapeado = new ArrayList<>();

        List<Partido> lista = lista = partidoRepository.findAll();

        for (Partido temp : lista) {

            partidomapeado.add(partidoMapper.EntityToDTO(temp));
        }
        return partidomapeado;
    }

    @Override
    public PartidoDTO getById(Long id) {

        Optional<Partido> partidoEntity = partidoRepository.findById(id);
        if (partidoEntity.isPresent()) {
            return partidoMapper.EntityToDTO(partidoEntity.get());
        } else {
            throw new NoSuchElementException("No existe el partido con ese id");
        }
    }

    @Override
    public PartidoDTO save(PartidoDTO partido) {

        if (partido.getId() != null && partidoRepository.existsById(partido.getId())) {
            throw new IllegalArgumentException("El partido con ese id ya existe");
        }
        if (!validarCanchasOcupadas(partido)) {
            throw new IllegalArgumentException("Debe haber un plazo de 4 horas entre partidos que usen la misma cancha");
        }

        Partido partidoEntity = partidoRepository.save(partidoMapper.DTOToEntity(partido));
        return partidoMapper.EntityToDTO(partidoEntity);
    }

    @Override
    public PartidoDTO update(PartidoDTO partido) {

        if (partidoRepository.existsById(partido.getId())) {

            Partido partidoEntity = partidoRepository.save(partidoMapper.DTOToEntity(partido));
            return partidoMapper.EntityToDTO(partidoEntity);
        } else {

            throw new NoSuchElementException("El partido con ese id no existe");
        }

    }

    @Override
    public void delete(Long id) {

        if (partidoRepository.existsById(id)) {

            partidoRepository.deleteById(id);

        } else {
            throw new NoSuchElementException("El partido con ese id no existe");
        }

    }

    private Boolean validarCanchasOcupadas(PartidoDTO partidoDTO) {


        List<Partido> canchasOcupadas = partidoRepository.findAll().stream()
                .filter(x -> x.getCancha().getId() == partidoDTO.getCancha().getId()).collect(Collectors.toList());

        Date fechaNueva = partidoDTO.getFechaComienzo();
        Calendar calendar = Calendar.getInstance();
        Calendar aComparar = Calendar.getInstance();

        for (Partido temp : canchasOcupadas) {
            aComparar.setTime(temp.getFechaComienzo());
            calendar.setTime(fechaNueva);


            Long hora = Math.abs(ChronoUnit.HOURS.between(calendar.toInstant(), aComparar.toInstant()));
            if (hora <= 4) {
                return false;
            }
        }
        return true;
    }

    //LOGICA PARA SUMAR PUNTOS!!! NO PASAR!

    //Int que se utilizan para validaciones
    public static final int SCORE_ADV = 4;
    public static final int SCORE_40 = 3;
    public static final int SCORE_30 = 2;
    public static final int SCORE_15 = 1;
    public static final int SCORE_0 = 0;

    //Map utilizado para guardar las descripciones y los distintos puntajes
    //Este es un hashmap, eso significa que tiene asociado un key y un valor
    //Por ejemplo el key SCORE_ADV tiene asociado el String Adv
    //El key SCORE_40 tiene asociado el string 40... etc
    private static final Map<Integer, String> descriptions = new HashMap<>();

    //Static utilizado para guardar en el map descriptions los valores de los distintos puntajes
    static {
        descriptions.put(SCORE_ADV, "Adv");
        descriptions.put(SCORE_40, "40");
        descriptions.put(SCORE_30, "30");
        descriptions.put(SCORE_15, "15");
        descriptions.put(SCORE_0, "0");
    }

    @Override
    public void initGame(Long id) {
        /*En este metodo se inicia un partido
         * se obtiene un opcional de partido llamando a findById con el id obtenido por parametro*/
        Optional<Partido> optPartido = partidoRepository.findById(id);
        if (optPartido.isPresent()) { //Se fija que en el opcional de partido haya un partido
            validarPartidoNoIniciado(id); //Se valida que el partido no este iniciado
            Partido partido = optPartido.get(); //Se obtiene el partido dentro de opcional y se lo guarda en una variable
            partido.setEstado(Estado.EN_CURSO); //Se le asigna el estado EN CURSO al partido
            partidoRepository.save(partido); //Se guarda el partido
        } else {
            throw new NoSuchElementException("No existe el partido con ese id"); //Si no habia partido presente se arroja excepcion
        }
    }

    @Override
    public PartidoDTO sumarPuntos(Long id, ModoJugador modo) {
        //En esta funcion se le suman puntos al jugador en el id de partido
        Optional<Partido> optPartido = partidoRepository.findById(id); //Se busca un partido en el repository
        if (optPartido.isPresent()) { //Verifica que el opcional el partido este presente
            Partido partido = optPartido.get(); //Se obtiene el partido del opcional y lo guarda en una variable temporal de tipo Partido

            if (!Estado.EN_CURSO.equals(partido.getEstado())) {  //Verifica que el partido este en curso
                //Si el partido no esta en curso arroja una excepcion
                throw new IllegalArgumentException("El partido no esta en progreso");
            }

            if (modo == ModoJugador.LOCAL) { //Si el que sumo puntos es el local
                if (partido.getScoreVisitante() == SCORE_ADV) { //Si el visitante tiene el score maximo
                    if (partido.getScoreLocal() != SCORE_40) { //Si el score es distinto de 40 arroja excepcion
                        throw new IllegalArgumentException("Score imposible");
                    }
                    partido.setScoreVisitante(partido.getScoreVisitante() - 1); //Setea el score del visitante al actual - 1
                } else { //Mismas logicas de arriba pero para el local
                    if (partido.getScoreLocal() == SCORE_ADV && partido.getScoreVisitante() != SCORE_40) {
                        throw new IllegalArgumentException("Score imposible");
                    }
                    partido.setScoreLocal(partido.getScoreLocal() + 1);
                }
            } else { //Mismas logicas de arriba pero para la suma de puntos del visitante
                if (partido.getScoreLocal() == SCORE_ADV) {
                    if (partido.getScoreVisitante() != SCORE_40) {
                        throw new IllegalArgumentException("Score imposible");
                    }
                    partido.setScoreLocal(partido.getScoreLocal() - 1);
                } else {
                    if (partido.getScoreVisitante() == SCORE_ADV && partido.getScoreLocal() != SCORE_40) {
                        throw new IllegalArgumentException("Score imposible");
                    }
                    partido.setScoreVisitante(partido.getScoreVisitante() + 1);
                }
            }
            this.actualizarScore(partido); //se llama al metodo actualizar score
            return this.partidoMapper.EntityToDTO(partidoRepository.save(partido)); //se guarda la entidad en el repository y se transforma el return a dto para response
        } else {
            throw new NoSuchElementException("No existe partido con ese id.");
        }
    }

    private void validarPartidoNoIniciado(Long id) {
        /*
         * En este metodo se valida el partido no iniciado, se obtiene el partido por el id recibido por parametro
         * y se lo aloja en una variable local, luego, en un if verifica que el estado del partido sea distinto a NO_INICIADO,
         * en caso positivo arroja una excepcion del tipo IllegalArgumentException*/
        PartidoDTO partido = this.getById(id);
        if (!Estado.NO_INICIADO.equals(partido.getEstado())) {
            throw new IllegalArgumentException("El partido ya esta en progreso.");
        }
    }

    private void actualizarScore(Partido partido) {
        partido.setPuntosGameActualLocal(this.translateScore(partido.getScoreLocal())); //Se traduce el puntaje del score local a puntos de game
        partido.setPuntosGameActualVisitante(this.translateScore(partido.getScoreVisitante())); //lo mismo de arriba para visitante

        //Math abs devuelve el valor absoluto, en el if verifica que sea mayor o igual a 2
        if (Math.abs(partido.getScoreLocal() - partido.getScoreVisitante()) >= 2) {
            if (partido.getScoreLocal() > partido.getScoreVisitante() && partido.getScoreLocal() >= SCORE_ADV) { //Si el score del local es mayor al visitante y tiene SCORE_ADV
                this.gameLocal(partido); //se llama al a funcion gameLocal
            } else if (partido.getScoreVisitante() > partido.getScoreLocal() && partido.getScoreVisitante() >= SCORE_ADV) { //Si el score del visitante es mayor al local y tiene SCORE_ADV
                this.gameVisitante(partido); //se llama a la funcion gameVisitante
            }
        }
    }

    private String translateScore(int puntos) {
        /*Este metodo traduce el puntaje recibido por int a un key del hashmap descriptions*/
        return descriptions.get(puntos);
    }

    private void gameLocal(Partido partido) {
        partido.setScoreLocal(0); //Se setean los puntos del score local a 0
        partido.setScoreVisitante(0); //se setean los puntos del score visitante a 0
        partido.setPuntosGameActualLocal(this.translateScore(partido.getScoreLocal())); //Se le cargan puntos al game local actual traducidos
        partido.setPuntosGameActualVisitante(this.translateScore(partido.getScoreVisitante()));//Se le cargan puntos al game visitante actual traducidos

        partido.setCantidadGamesLocal(partido.getCantidadGamesLocal() + 1); //Se le aumenta un game al local (ya que anoto el maximo puntaje)
        if (partido.getCantidadGamesLocal() == 6) { //Si llega a 6 rondas ganadas se finaliza el partido
            partido.setEstado(Estado.FINALIZADO);
        }
    }

    private void gameVisitante(Partido partido) {
        partido.setScoreLocal(0); //Se setean los puntos del score local a 0
        partido.setScoreVisitante(0); //se setean los puntos del score visitante a 0
        partido.setPuntosGameActualLocal(this.translateScore(partido.getScoreLocal())); //Se le cargan puntos al game local actual traducidos
        partido.setPuntosGameActualVisitante(this.translateScore(partido.getScoreVisitante())); //Se le cargan puntos al game visitante actual traducidos

        partido.setCantidadGamesVisitante(partido.getCantidadGamesVisitante() + 1); //Se le aumenta un game al visitante (ya que anoto el maximo puntaje)
        if (partido.getCantidadGamesVisitante() == 6) { //Si llega a 6 rondas ganadas se finaliza el partido
            partido.setEstado(Estado.FINALIZADO);
        }
    }
}