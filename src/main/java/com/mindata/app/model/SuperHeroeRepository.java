package com.mindata.app.model;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import java.util.List;
import org.springframework.data.repository.query.Param;

@RepositoryRestResource(collectionResourceRel = "superheroe", path = "superheroe")
public interface SuperHeroeRepository extends PagingAndSortingRepository<SuperHeroe, Long> {

    List<SuperHeroe> findByName(@Param("name") String name);

}
