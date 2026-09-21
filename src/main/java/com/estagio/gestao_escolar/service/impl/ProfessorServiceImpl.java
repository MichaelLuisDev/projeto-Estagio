package com.estagio.gestao_escolar.service.impl;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.mapper.ProfessorMapper;
import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.model.Professor;
import com.estagio.gestao_escolar.repository.CursoRepository;
import com.estagio.gestao_escolar.repository.ProfessorRepository;
import com.estagio.gestao_escolar.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final CursoRepository cursoRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper, CursoRepository cursoRepository) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.cursoRepository = cursoRepository;
    }
    @Override
    public ProfessorDto cadastraProfessor(Long cursoId, ProfessorDto professorDto) {
        if (professorDto != null) {
            Professor professor = professorMapper.toEntity(professorDto);
            if (cursoId != null) {
                Curso cursoVinculado = cursoRepository.findById(cursoId).orElse(null);
                if (cursoVinculado != null) {
                    professor.getCursos().add(cursoVinculado);
                    cursoVinculado.setProfessor(professor);
                }
            }
            professorRepository.save(professor);
            return professorMapper.toDto(professor);
        }
        throw new RuntimeException("Erro ao cadastrar professor");
    }


    @Override
    public List<ProfessorDto> retornaListaDeProfessores() {
        return professorMapper.toDtoList(professorRepository.findAll());
    }

    @Override
    public ProfessorDto atualizaProfessor(long id, ProfessorDto professor) {
        if (professor == null) {
            throw new IllegalArgumentException("Entrada inválida");
        }
        Professor professorDesatualizado = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID não encontrado"));
        professorMapper.updateEntityFromDto(professorDesatualizado, professor);
        return professorMapper.toDto(professorRepository.save(professorDesatualizado));
    }

    @Override
    public void deletaProfessor(long id) {
        Professor professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ID inexistente"));

        professorRepository.delete(professor);
    }










}
