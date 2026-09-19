package com.estagio.gestao_escolar.service.impl;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.mapper.CursoMapper;
import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.repository.CursoRepository;
import com.estagio.gestao_escolar.service.CursoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {
    private final CursoRepository cursoRepository;
    private final CursoMapper cursoMapper;

    public CursoServiceImpl(CursoRepository cursoRepository, CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.cursoMapper = cursoMapper;
    }


    // Retorna a lista de todos os cursos existente
    @Override
    public List<CursoDto> retornaListaDeCursos() {

        return cursoMapper.toDtoList(cursoRepository.findAll());
    }

    //Retorna o curso pelo id especifico
    @Override
    public CursoDto retornaCursoPorId(long id) {
        return cursoMapper.toDto(cursoRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Curso com ID " + id + " não encontrado.")));
    }
    //Cadastra um novo curso
    @Override
    public CursoDto cadastraNovoCurso(CursoDto curso) {
        if (curso == null) {
            throw new IllegalArgumentException("O curso não pode ser nulo.");
        }
        return cursoMapper.toDto(cursoRepository.save(cursoMapper.toEntity(curso)));
    }

    // Atualiza um curso existente
    @Override
    public CursoDto atualizaCurso(long id, CursoDto cursoAtualizado) {
        Curso cursoExistente = cursoMapper.toEntity(retornaCursoPorId(id));
        cursoExistente.setNomeCurso(cursoAtualizado.curso_Nome());
        cursoExistente.setDescricaoCurso(cursoAtualizado.curso_Descricao());
        // Atualize outros campos se necessário (ex: status)

        return cursoMapper.toDto(cursoRepository.save(cursoExistente));
    }
    //Delete curso por id
    public void deletaCurso(long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso com ID " + id + " não encontrado para exclusão.");
        }
        cursoRepository.deleteById(id);
    }
    //retorna todos os cursos por status
    public List<CursoDto>retornaListaDeCursosPorStatus(boolean status) {
        return cursoMapper.toDtoList(cursoRepository.findByStatusAtivo(status));
    }
}