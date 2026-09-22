package com.estagio.gestao_escolar.service;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.model.Aluno;

import java.util.List;

public interface AlunoService {
    public AlunoDto cadastraAluno(AlunoDto aluno);
    public List<AlunoDto> buscaTodosOsAlunos();
    public AlunoDto buscaAlunoPorId(Long id);
    public AlunoDto atualizaCadastroAluno(Long id, AlunoDto aluno);
    public void removeAluno(Long id);
}
