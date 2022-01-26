package com.mindata.app.rest;

import com.mindata.app.mapper.SuperHeroeMapper;
import com.mindata.app.model.SuperHeroe;
import com.mindata.app.model.SuperHeroeRepository;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api")
public class SuperHeroeResource {

    @Autowired
    SuperHeroeRepository superHeroeRepository;



    public SuperHeroeResource() {

    }

    @PostMapping("/new")
    public ResponseEntity<SuperHeroeDTO> createSuperHeroe(@Valid @RequestBody SuperHeroeDTO superHeroeDTO) {
        SuperHeroe superHeroe = SuperHeroeMapper.INSTANCE.dtoToEntity(superHeroeDTO);
        superHeroeRepository.save(superHeroe);
        return new ResponseEntity<>(SuperHeroeMapper.INSTANCE.entitytoDto(superHeroe), HttpStatus.CREATED );
    }
}
