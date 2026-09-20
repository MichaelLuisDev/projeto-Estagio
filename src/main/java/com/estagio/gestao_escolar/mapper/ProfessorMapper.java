package com.estagio.gestao_escolar.mapper;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.model.Professor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfessorMapper extends BaseMapper<Professor, ProfessorDto> {
}
