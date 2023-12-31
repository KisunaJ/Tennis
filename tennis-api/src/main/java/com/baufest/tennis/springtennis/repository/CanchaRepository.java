package com.baufest.tennis.springtennis.repository;

import com.baufest.tennis.springtennis.model.Cancha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<Cancha, Long> {
}
