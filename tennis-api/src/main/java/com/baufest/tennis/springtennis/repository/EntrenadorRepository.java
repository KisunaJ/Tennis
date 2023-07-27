package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.model.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
}
