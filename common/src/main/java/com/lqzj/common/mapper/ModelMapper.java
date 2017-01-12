package com.lqzj.common.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import java.util.List;

public class ModelMapper {
    protected final MapperFactory mapperFactory;

    public ModelMapper() {
        mapperFactory = new DefaultMapperFactory.Builder().build();
    }

    public <T> T map(Object obj, Class<T> targetType) {
        return mapperFactory.getMapperFacade().map(obj, targetType);
    }

    public <T> List<T> mapToList(List<?> source, Class<T> targetType) {
        return mapperFactory.getMapperFacade().mapAsList(source, targetType);
    }
}
