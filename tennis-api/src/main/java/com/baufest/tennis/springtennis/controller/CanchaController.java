package com.baufest.tennis.springtennis.controller;


import com.baufest.tennis.springtennis.dto.CanchaDTO;
import com.baufest.tennis.springtennis.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("springtennis/api/v1/canchas")
public class CanchaController {

    public final CanchaService canchaService;

    @Autowired
    public CanchaController(CanchaService canchaService) {
        this.canchaService = canchaService;
    }

    @GetMapping
    public ResponseEntity<List<CanchaDTO>> listAll(){

        return ResponseEntity.ok(canchaService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CanchaDTO> getById(@PathVariable  Long id){

        return ResponseEntity.ok(canchaService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CanchaDTO> save(@RequestBody CanchaDTO cancha){

        return ResponseEntity.ok(canchaService.save(cancha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanchaDTO> update(@PathVariable Long id, @RequestBody CanchaDTO cancha){

        cancha.setId(id);
        return ResponseEntity.ok(canchaService.update(cancha));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete( @PathVariable Long id){
        canchaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
