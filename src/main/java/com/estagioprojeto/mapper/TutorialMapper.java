package com.estagioprojeto.mapper;

import com.estagioprojeto.dto.TutorialDto;
import com.estagioprojeto.model.Tutorial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TutorialMapper extends BaseMapper<Tutorial, TutorialDto>{

}
