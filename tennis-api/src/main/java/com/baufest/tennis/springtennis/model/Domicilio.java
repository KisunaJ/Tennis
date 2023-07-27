package com.baufest.tennis.springtennis.model;

import javax.persistence.*;

@Entity
@Table(name = "Domicilio")
public class Domicilio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column private String Direccion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_personas", referencedColumnName = "id")
    private Persona personas ;


    // @ManyToOne()
   // @JoinColumn(name = "id_persona")
   // private Persona persona1;

   // @ManyToOne()
   // @JoinColumn(name = "id_persona")
    //private Persona personas;



}