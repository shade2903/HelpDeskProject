package com.project.haiduk.converter;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;

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
}
