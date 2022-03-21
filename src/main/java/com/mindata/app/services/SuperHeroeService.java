package com.mindata.app.services;

import com.mindata.app.model.SuperHeroe;
import com.mindata.app.model.SuperHeroeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperHeroeService {

    @Autowired
    private SuperHeroeRepository superHeroeRepository;

    public List<SuperHeroe> obtenerTodos() {
        return superHeroeRepository.findAll();
    }

    public Optional<SuperHeroe> obtenerPorId(Long id) {
        return superHeroeRepository.findById(id);
    }

    public Optional<SuperHeroe> modificarSuperHeroe(@NonNull SuperHeroe superHeroe) {
        Optional<SuperHeroe> superHeroe1 = superHeroeRepository.findById(superHeroe.getId());
        if(superHeroe1.isPresent()) {
            SuperHeroe superHeroeAModificar = superHeroe1.get();
            superHeroeAModificar.setNombre( superHeroe.getNombre() );
            SuperHeroe s = superHeroeRepository.save(superHeroeAModificar);
            return Optional.of(s);
        } else {
            return Optional.empty();
        }
    }
}
