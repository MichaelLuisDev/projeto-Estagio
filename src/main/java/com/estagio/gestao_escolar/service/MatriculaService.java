package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.MatriculaDto;

import java.util.List;

public interface MatriculaService {

    public MatriculaDto cadastraMatricula(Long aluno, Long curso);
    public List<MatriculaDto> buscaTodasAsMatriculas();
    public MatriculaDto buscaMatriculaPorId(Long id);
    public MatriculaDto atualizaMatricula(Long matriculaId, MatriculaDto matricula);
    public void removeMatricula(Long id);
}
