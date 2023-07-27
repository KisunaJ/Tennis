package com.baufest.tennis.springtennis.dto;

public class EntrenadorDTO {


    private Long id;
    private String Nombre;


    public EntrenadorDTO() {
    }

    public EntrenadorDTO(Long id, String nombre) {
        this.id = id;
        Nombre = nombre;
       ;
    }

    public EntrenadorDTO(String nombre) {
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
