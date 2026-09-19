package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.model.Curso;

import java.util.List;

public interface CursoService {

    List<CursoDto> retornaListaDeCursos();
    CursoDto retornaCursoPorId(long id);
    CursoDto cadastraNovoCurso(CursoDto curso);
    CursoDto atualizaCurso(long id, CursoDto curso);
    void deletaCurso(long id);
    List<CursoDto> retornaListaDeCursosPorStatus(boolean status);
}
