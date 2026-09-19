package com.estagio.gestao_escolar.repository;

import com.estagio.gestao_escolar.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByStatusAtivo(boolean isAtivo);
    List<Curso> findByNomeCursoContaining(String nome);

    @Query("SELECT c FROM Curso c WHERE LOWER(c.nomeCurso) LIKE LOWER(CONCAT('%', :nome, '%'))")
    List<Curso> buscarPorNome(@Param("nome") String nome);
}