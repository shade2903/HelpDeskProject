package com.project.haiduk.converter;

import com.project.haiduk.exception.DataNotFoundException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractConverter<E, D> {

    @Autowired
    private MapperFacade mapperFacade;

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public void setMapperFacade(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    abstract Class<D> getDomainClass();

    abstract  Class<E> getEntityClass();

    public D toDto(E entity){
        return mapperFacade.map(entity,getDomainClass());
    }

    public E fromDto(D dto){
        return mapperFacade.map(dto, getEntityClass());
    }

    public List<D> toDtoList(List<E> entities){
        List<D> listDto = new ArrayList<>();
        if(entities == null || entities.isEmpty()){
            throw new DataNotFoundException("Data not found!");
        }
        for(E entity : entities){
            listDto.add(toDto(entity));
        }
        return listDto;
    }

    public List<E> toEntityList(List<D> listDto){
        List<E> entities = new ArrayList<>();
        if(listDto == null || listDto.isEmpty()){
            throw new DataNotFoundException("Data not found!");
        }
        for(D dto : listDto){
            entities.add(fromDto(dto));
        }
        return entities;
    }
}
