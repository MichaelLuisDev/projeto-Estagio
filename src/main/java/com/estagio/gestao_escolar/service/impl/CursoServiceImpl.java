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
        Curso novoCurso = cursoMapper.toEntity(curso);
        //Curso novoCurso = new Curso();
        //novoCurso.setNome(curso.nome());
        //novoCurso.setDescricao(curso.descricao());
       // novoCurso.setStatusAtivo(true);
        cursoRepository.save(novoCurso);
        return cursoMapper.toDto(novoCurso);
    }
    // Atualiza um curso existente
    @Override
    public CursoDto atualizaCurso(long id, CursoDto cursoAtualizado) {
        if (cursoAtualizado == null) {
            throw new IllegalArgumentException("Entrada inválida.");
        }
        //Curso cursoExistente = cursoRepository.encontrarPorId(id);
        //cursoMapper.updateEntityFromDto(cursoExistente, cursoAtualizado);
       // return cursoMapper.toDto(cursoRepository.save(cursoExistente));

        // 1. Busca a entidade existente no banco de dados
        Curso cursoExistente = cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso com ID " + id + " não encontrado para atualização."));

        // 2. Atualiza os campos manualmente
        if (cursoAtualizado.nome() != null) {
            cursoExistente.setNome(cursoAtualizado.nome());
        }
        if (cursoAtualizado.descricao() != null) {
            cursoExistente.setDescricao(cursoAtualizado.descricao());
        }
        // 3. Salva a entidade atualizada no banco de dados
        Curso cursoSalvo = cursoRepository.save(cursoExistente);
        // 4. CORREÇÃO: Retorna o DTO gerado a partir da entidade salva e atualizada
        return cursoMapper.toDto(cursoSalvo);
    }
    //Delete curso por id
    @Override
    public void deletaCurso(long id) {
        if (!cursoRepository.existsById(id)) {
            throw new EntityNotFoundException("Curso com ID " + id + " não encontrado para exclusão.");
        }
        cursoRepository.deleteById(id);
    }
    //DELETA TUDO NÃO USE
    @Override
    public void deletaTudo(){
        cursoRepository.deleteAll();
    }

    //retorna todos os cursos por status
    @Override
    public List<CursoDto>retornaListaDeCursosPorStatus(boolean status) {
        return cursoMapper.toDtoList(cursoRepository.findByStatusAtivo(status));
    }
}