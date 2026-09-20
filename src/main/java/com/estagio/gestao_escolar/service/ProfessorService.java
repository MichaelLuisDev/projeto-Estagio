package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.model.Professor;

import java.util.List;

public interface ProfessorService {

    ProfessorDto cadastraProfessor(ProfessorDto professor);
    List<ProfessorDto> retornaListaDeProfessores();
    ProfessorDto atualizaProfessor(long id, ProfessorDto professor);
    void deletaProfessor(long id);


}
