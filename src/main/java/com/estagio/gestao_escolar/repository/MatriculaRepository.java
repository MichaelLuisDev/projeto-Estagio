package com.estagio.gestao_escolar.repository;

import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    @Query("SELECT c FROM Matricula c WHERE c.status = :status")
    List<Matricula> buscarPorStatus(@Param("status") boolean status);
}
