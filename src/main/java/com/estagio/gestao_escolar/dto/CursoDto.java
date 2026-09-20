package com.estagio.gestao_escolar.dto;

public record CursoDto(
        String nome,
        String descricao,
        boolean statusAtivo
) {}
