package com.mindata.app.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "superheroe", path = "superheroe")
public interface SuperHeroeRepository extends JpaRepository<SuperHeroe, Long> {

    List<SuperHeroe> findByNombreContaining(String nombre);

}
