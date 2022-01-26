package com.mindata.app.mapper;

import com.mindata.app.model.SuperHeroe;
import com.mindata.app.rest.SuperHeroeDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SuperHeroeMapper {

    SuperHeroeMapper INSTANCE = Mappers.getMapper(SuperHeroeMapper.class);

    SuperHeroe dtoToEntity(SuperHeroeDTO dto);

    SuperHeroeDTO entitytoDto(SuperHeroe entity);

    /*@InheritInverseConfiguration
    SuperHeroeDTO entityToDto(SuperHeroe target);*/
}
