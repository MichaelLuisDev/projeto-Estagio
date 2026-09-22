package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.model.Curso;

import java.util.List;

public interface CursoService {

    List<CursoDto> retornaListaDeCursos();
    CursoDto retornaCursoPorId(Long id);
    CursoDto cadastraNovoCurso(Long professorId, CursoDto curso);
    CursoDto atualizaCurso(Long id, CursoDto curso);
    void deletaCurso(Long id);
    void deletaTudo();
    List<CursoDto> retornaListaDeCursosPorStatus(boolean status);

}
