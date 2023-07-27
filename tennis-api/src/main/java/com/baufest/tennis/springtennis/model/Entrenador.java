package com.baufest.tennis.springtennis.model;


import javax.persistence.*;

@Entity
@Table(name= "Entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Nombre;

    public Entrenador() {
    }

    public Entrenador(Long id, String nombre) {
        this.id = id;
        Nombre = nombre;
    }

    public Entrenador(String nombre) {
        Nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}

