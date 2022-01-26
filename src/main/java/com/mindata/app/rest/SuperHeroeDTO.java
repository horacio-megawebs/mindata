package com.mindata.app.rest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuperHeroeDTO {
    private int id;
    private String nombre;
}
