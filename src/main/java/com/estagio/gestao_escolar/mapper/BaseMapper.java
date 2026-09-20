package com.estagio.gestao_escolar.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Entity,Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);

    List<Dto> toDtoList(List<Entity> entities);
    List<Entity> toEntityList(List<Dto> dto);


    @Mapping(target = "id", ignore = true)
    void updateEntityFromDto(@MappingTarget Entity entity, Dto dto);
}
