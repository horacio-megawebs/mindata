package com.mindata.app.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class SuperHeroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

}
