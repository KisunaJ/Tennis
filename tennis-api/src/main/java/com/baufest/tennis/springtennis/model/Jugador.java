package com.baufest.tennis.springtennis.model;

import org.json.JSONObject;
import javax.persistence.*;


@Entity
@Table(name = "Jugador")
public class Jugador{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private int puntos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEntrenador")
	private Entrenador entrenador;

	public Jugador() {
	}

	public Jugador(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public Jugador(Long id, String nombre, int puntos) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
	}

	public Jugador(Long id, String nombre, int puntos, Entrenador entrenador) {
		this.id = id;
		this.nombre = nombre;
		this.puntos = puntos;
		this.entrenador = entrenador;
	}

	public Jugador(String nombre, int puntos, Entrenador entrenador) {
		this.nombre = nombre;
		this.puntos = puntos;
		this.entrenador = entrenador;
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

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
}

