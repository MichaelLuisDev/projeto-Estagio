package com.estagio.gestao_escolar.service.impl;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.mapper.AlunoMapper;
import com.estagio.gestao_escolar.model.Aluno;
import com.estagio.gestao_escolar.model.Professor;
import com.estagio.gestao_escolar.repository.AlunoRepository;
import com.estagio.gestao_escolar.repository.CursoRepository;
import com.estagio.gestao_escolar.repository.ProfessorRepository;
import com.estagio.gestao_escolar.service.AlunoService;
import com.estagio.gestao_escolar.service.CursoService;
import com.estagio.gestao_escolar.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;
    private final CursoRepository cursoRepository;
    private final ProfessorRepository professorRepository;
    private final CursoService cursoService;
    private final ProfessorService professorService;

    public AlunoServiceImpl(AlunoRepository alunoRepository,
                            AlunoMapper alunoMapper,
                            CursoRepository cursoRepository,
                            ProfessorRepository professorRepository,
                            CursoService cursoService,
                            ProfessorService professorService) {
        this.alunoRepository = alunoRepository;
        this.alunoMapper = alunoMapper;
        this.cursoRepository = cursoRepository;
        this.professorRepository = professorRepository;
        this.cursoService = cursoService;
        this.professorService = professorService;
    }

    @Override
    public AlunoDto cadastraAluno(AlunoDto aluno) {
        if(aluno == null){
            throw new IllegalArgumentException("A entrada é inválida");
        }
        Aluno alunoCadastrado = alunoMapper.toEntity(aluno);
        alunoRepository.save(alunoCadastrado);
        return alunoMapper.toDto(alunoCadastrado);
    }

    @Override
    public List<AlunoDto> buscaTodosOsAlunos() {
        return alunoMapper.toDtoList(alunoRepository.findAll());
    }

    @Override
    public AlunoDto buscaAlunoPorId(Long id) {
        if(id == null){
            throw new IllegalArgumentException("O ID é inválido");
        }
        if(!alunoRepository.existsById(id)){
            throw new EntityNotFoundException("O aluno não foi encontrado");
        }
        return alunoMapper.toDto(alunoRepository.findById(id).get());
    }

    @Override
    public AlunoDto atualizaCadastroAluno(Long id, AlunoDto aluno) {
        if (aluno == null) {
            throw new IllegalArgumentException("Entrada inválida");
        }
        Aluno alunoDesatualizado = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
        alunoMapper.updateEntityFromDto(alunoDesatualizado, aluno);
        return alunoMapper.toDto(alunoRepository.save(alunoDesatualizado));
    }

    public void removeAluno(Long id) {
        if (alunoRepository.existsById(id)) {
            alunoRepository.deleteById(id);
        }
    }
}
