package com.baufest.tennis.springtennis.model;

import com.baufest.tennis.springtennis.enums.Estado;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Partido")
public class Partido {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date fechaComienzo;

    @Column(nullable = false)
    private Estado estado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idLocal", nullable = false)
    private Jugador jugadorLocal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idVisitante", nullable = false)
    private Jugador jugadorVisitante;

    @Column(nullable = true)
    private int scoreLocal;

    @Column(nullable = true)
    private int scoreVisitante;

    @Column(nullable = true)
    private String puntosGameActualLocal;

    @Column(nullable = true)
    private String puntosGameActualVisitante;

    @Column(nullable = true)
    private int cantidadGamesLocal;

    @Column(nullable = true)
    private int cantidadGamesVisitante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCancha", nullable = false)
    private Cancha cancha;


    public Partido() {
    }

    public Partido(Long id, Date fechaComienzo, Estado estado, Jugador jugadorLocal,
                   Jugador jugadorVisitante, int scoreLocal, int scoreVisitante, String puntosGameActualLocal,
                   String puntosGameActualVisitante, int cantidadGamesLocal, int cantidadGamesVisitante,Cancha cancha) {
        this.id = id;
        this.fechaComienzo = fechaComienzo;
        this.estado = estado;
        this.jugadorLocal = jugadorLocal;
        this.jugadorVisitante = jugadorVisitante;
        this.scoreLocal = scoreLocal;
        this.scoreVisitante = scoreVisitante;
        this.puntosGameActualLocal = puntosGameActualLocal;
        this.puntosGameActualVisitante = puntosGameActualVisitante;
        this.cantidadGamesLocal = cantidadGamesLocal;
        this.cantidadGamesVisitante = cantidadGamesVisitante;
        this.cancha = cancha;
    }

    public Partido(Date fechaComienzo, Estado estado, Jugador jugadorLocal, Jugador jugadorVisitante,
                   int scoreLocal, int scoreVisitante, String puntosGameActualLocal, String puntosGameActualVisitante,
                   int cantidadGamesLocal, int cantidadGamesVisitante,Cancha cancha) {
        this.fechaComienzo = fechaComienzo;
        this.estado = estado;
        this.jugadorLocal = jugadorLocal;
        this.jugadorVisitante = jugadorVisitante;
        this.scoreLocal = scoreLocal;
        this.scoreVisitante = scoreVisitante;
        this.puntosGameActualLocal = puntosGameActualLocal;
        this.puntosGameActualVisitante = puntosGameActualVisitante;
        this.cantidadGamesLocal = cantidadGamesLocal;
        this.cantidadGamesVisitante = cantidadGamesVisitante;
        this.cancha = cancha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Jugador getJugadorLocal() {
        return jugadorLocal;
    }

    public void setJugadorLocal(Jugador jugadorLocal) {
        this.jugadorLocal = jugadorLocal;
    }

    public Jugador getJugadorVisitante() {
        return jugadorVisitante;
    }

    public void setJugadorVisitante(Jugador jugadorVisitante) {
        this.jugadorVisitante = jugadorVisitante;
    }

    public int getScoreLocal() {
        return scoreLocal;
    }

    public void setScoreLocal(int scoreLocal) {
        this.scoreLocal = scoreLocal;
    }

    public int getScoreVisitante() {
        return scoreVisitante;
    }

    public void setScoreVisitante(int scoreVisitante) {
        this.scoreVisitante = scoreVisitante;
    }

    public String getPuntosGameActualLocal() {
        return puntosGameActualLocal;
    }

    public void setPuntosGameActualLocal(String puntosGameActualLocal) {
        this.puntosGameActualLocal = puntosGameActualLocal;
    }

    public String getPuntosGameActualVisitante() {
        return puntosGameActualVisitante;
    }

    public void setPuntosGameActualVisitante(String puntosGameActualVisitante) {
        this.puntosGameActualVisitante = puntosGameActualVisitante;
    }

    public int getCantidadGamesLocal() {
        return cantidadGamesLocal;
    }

    public void setCantidadGamesLocal(int cantidadGamesLocal) {
        this.cantidadGamesLocal = cantidadGamesLocal;
    }

    public int getCantidadGamesVisitante() {
        return cantidadGamesVisitante;
    }

    public void setCantidadGamesVisitante(int cantidadGamesVisitante) {
        this.cantidadGamesVisitante = cantidadGamesVisitante;
    }

    public Cancha getCancha() {
        return cancha;
    }

    public void setCancha(Cancha cancha) {
        this.cancha = cancha;
    }
}
