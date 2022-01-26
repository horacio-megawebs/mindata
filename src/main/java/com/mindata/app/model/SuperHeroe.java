package com.mindata.app.model;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class SuperHeroe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

}
