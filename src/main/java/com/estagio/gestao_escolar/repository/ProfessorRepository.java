package com.estagio.gestao_escolar.repository;

import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("SELECT p FROM Professor p WHERE p.id = :id")
    Professor encontrarPorId(@Param("id") long id);

    @Query("SELECT p FROM Professor p WHERE LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Professor> buscarPorNome(@Param("nome") String nome);
}
