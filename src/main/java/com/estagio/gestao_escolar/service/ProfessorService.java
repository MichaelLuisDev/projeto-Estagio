package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.model.Professor;

import java.util.List;

public interface ProfessorService {
    List<ProfessorDto> retornaTodosOsProfessores();
    ProfessorDto retornaProfessorPorId(long id);
    List<ProfessorDto> retornaProfessorPorNome(String nome);
    List<ProfessorDto> retornaProfessorPorEspecialidade(String especialidade);
    ProfessorDto cadastraProfessor(ProfessorDto professor);
    ProfessorDto atualizaProfessor(long id, ProfessorDto professorDto);
    void deletaProfessor(long id);

}
