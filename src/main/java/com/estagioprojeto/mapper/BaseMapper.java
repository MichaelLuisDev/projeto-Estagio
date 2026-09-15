package com.estagioprojeto.mapper;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<Entity, Dto> {
    Dto toDto(Entity entity);
    Entity toEntity(Dto dto);

    List<Dto> toDtoList(List<Entity> entities);
    List<Entity> toEntityList(List<Dto> dto);

    void updateEntityFromDto(@MappingTarget Entity entity, Dto dto);
}
