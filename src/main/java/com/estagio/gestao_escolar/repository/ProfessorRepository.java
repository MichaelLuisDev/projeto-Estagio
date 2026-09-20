package com.estagio.gestao_escolar.repository;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {


    @Query("SELECT p FROM Professor p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Professor> encontrarPorNome(@Param("nome") String nome);
    @Query("SELECT p FROM Professor p WHERE LOWER(p.especialidade) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Professor> encontrarPorEspecialidade(@Param("especialidade") String especialidade);


}
