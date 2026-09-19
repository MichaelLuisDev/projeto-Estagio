package com.estagio.gestao_escolar.mapper;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.model.Curso;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CursoMapper extends BaseMapper<Curso, CursoDto> {

}
