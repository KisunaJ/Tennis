package com.baufest.tennis.springtennis.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @OneToOne(mappedBy = "personas")
    private Domicilio domicilio ;


    //@OneToMany(mappedBy = "Persona", cascade = CascadeType.ALL)
    //private List<Domicilio> domicilio1;

    //@OneToMany(mappedBy = "personas", cascade = CascadeType.ALL)
    //private List <Domicilio> domicilios;

}
