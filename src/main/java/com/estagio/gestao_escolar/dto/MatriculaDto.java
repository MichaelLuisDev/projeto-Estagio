package com.estagio.gestao_escolar.dto;

import java.time.LocalDate;

public record MatriculaDto(
        Long id,
        //Long alunoId,
        //Long cursoId,
        LocalDate dataDeInscricao,
        boolean status
) {}
