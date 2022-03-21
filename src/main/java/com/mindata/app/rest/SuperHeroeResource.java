package com.mindata.app.rest;

import com.mindata.app.mapper.SuperHeroeMapper;
import com.mindata.app.model.SuperHeroe;
import com.mindata.app.model.SuperHeroeRepository;
import com.mindata.app.services.SuperHeroeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/superheroe")
public class SuperHeroeResource {

    @Autowired
    SuperHeroeRepository superHeroeRepository;

    @Autowired
    SuperHeroeService superHeroeService;


    @GetMapping("/")
    public ResponseEntity<List<SuperHeroeDTO>> getAll() {
        List<SuperHeroe> superHeroeList = superHeroeRepository.findAll();
        List<SuperHeroeDTO> superHeroeDTOList = superHeroeList.stream()
                                                                .map( s -> SuperHeroeMapper.INSTANCE.entitytoDto(s) )
                                                                .collect(Collectors.toList());
        return new ResponseEntity<>(superHeroeDTOList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<SuperHeroeDTO> createSuperHeroe(@Valid @RequestBody SuperHeroeDTO superHeroeDTO) {
        SuperHeroe superHeroe = SuperHeroeMapper.INSTANCE.dtoToEntity(superHeroeDTO);
        superHeroeRepository.save(superHeroe);
        return new ResponseEntity<>(SuperHeroeMapper.INSTANCE.entitytoDto(superHeroe), HttpStatus.CREATED );
    }

    @PutMapping("/update")
    public ResponseEntity<SuperHeroeDTO> updateSuperHero(@Valid @RequestBody SuperHeroeDTO superHeroeDTO) {
        SuperHeroe superHeroe = SuperHeroeMapper.INSTANCE.dtoToEntity(superHeroeDTO);
        Optional<SuperHeroe> s = superHeroeService.modificarSuperHeroe( superHeroe );
        if( s.isPresent() ) {
            return new ResponseEntity<>(SuperHeroeMapper.INSTANCE.entitytoDto(s.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<SuperHeroeDTO>(new SuperHeroeDTO(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteSuperHeroe(@Valid @RequestBody SuperHeroeDTO superHeroeDTO) {
        Optional<SuperHeroe> s = superHeroeRepository.findById( superHeroeDTO.getId() );
        if( s.isPresent() ) {
            superHeroeRepository.delete(s.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
