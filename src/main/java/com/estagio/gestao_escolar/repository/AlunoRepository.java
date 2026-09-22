package com.estagio.gestao_escolar.repository;

import com.estagio.gestao_escolar.model.Aluno;
import com.estagio.gestao_escolar.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT c FROM Aluno c WHERE LOWER(c.nome) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Aluno> buscarPorNome(@Param("nome") String nome);
}
