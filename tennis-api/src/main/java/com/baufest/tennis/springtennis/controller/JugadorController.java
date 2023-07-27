package com.baufest.tennis.springtennis.controller;

import com.baufest.tennis.springtennis.dto.JugadorDTO;
import com.baufest.tennis.springtennis.service.JugadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/jugadores")
public class JugadorController {


    private final JugadorService jugadorService;

    @Autowired
    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping
    public ResponseEntity<List<JugadorDTO>> listAll() {
        return ResponseEntity.ok(jugadorService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JugadorDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.getById(id));
    }
    @PostMapping
    public ResponseEntity<JugadorDTO> saveJugador(@RequestBody JugadorDTO jugador) {

        JugadorDTO savedJugador = jugadorService.save(jugador);
        return new ResponseEntity<>(savedJugador, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<JugadorDTO> updateJugador(@PathVariable Long id, @RequestBody JugadorDTO jugador) {

        JugadorDTO updatedJugador = jugadorService.update(jugador);
        return ResponseEntity.ok(updatedJugador);
    }
    @PutMapping("/calcularRanking/{id}")
    public ResponseEntity<JugadorDTO> calcularRanking(@PathVariable Long id){

        JugadorDTO updatedJugador = jugadorService.calcularRanking(id);
        return ResponseEntity.ok(updatedJugador);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJugador(@PathVariable Long id) {
        jugadorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
	


