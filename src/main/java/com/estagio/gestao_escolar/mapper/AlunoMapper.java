package com.estagio.gestao_escolar.mapper;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.model.Aluno;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AlunoMapper extends BaseMapper<Aluno, AlunoDto> {
}
