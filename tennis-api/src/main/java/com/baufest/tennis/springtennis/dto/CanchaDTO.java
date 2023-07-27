package com.baufest.tennis.springtennis.dto;



public class CanchaDTO {


    private Long id;
    private String nombre;
    private String direccion;
    private int cantidadPartidos;

    public CanchaDTO() {
    }

    public CanchaDTO(Long id, String nombre, String direccion, int cantidadPartidos) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadPartidos = cantidadPartidos;
    }

    public CanchaDTO(String nombre, String direccion, int cantidadPartidos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.cantidadPartidos = cantidadPartidos;
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

    public int getCantidadPartidos() {
        return cantidadPartidos;
    }

    public void setCantidadPartidos(int cantidadPartidos) {
        this.cantidadPartidos = cantidadPartidos;
    }
}
