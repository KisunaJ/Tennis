package com.baufest.tennis.springtennis.model;

import javax.persistence.*;


@Entity
@Table(name = "Cancha")
public class Cancha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String direccion;


    public Cancha() {
    }

    public Cancha(Long id, String nombre, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
