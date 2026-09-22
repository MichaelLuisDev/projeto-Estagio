package com.estagio.gestao_escolar.mapper;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.MatriculaDto;
import com.estagio.gestao_escolar.model.Aluno;
import com.estagio.gestao_escolar.model.Matricula;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatriculaMapper extends BaseMapper<Matricula, MatriculaDto> {
}
