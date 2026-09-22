package com.estagio.gestao_escolar.service.impl;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.MatriculaDto;
import com.estagio.gestao_escolar.mapper.AlunoMapper;
import com.estagio.gestao_escolar.mapper.CursoMapper;
import com.estagio.gestao_escolar.mapper.MatriculaMapper;
import com.estagio.gestao_escolar.model.Aluno;
import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.model.Matricula;
import com.estagio.gestao_escolar.repository.AlunoRepository;
import com.estagio.gestao_escolar.repository.CursoRepository;
import com.estagio.gestao_escolar.repository.MatriculaRepository;
import com.estagio.gestao_escolar.service.MatriculaService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class MatriculaServiceImpl implements MatriculaService {
    private final MatriculaRepository matriculaRepository;
    private final MatriculaMapper matriculaMapper;
    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final AlunoMapper alunoMapper;
    private final CursoMapper cursoMapper;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository,
                                MatriculaMapper matriculaMapper,
                                AlunoRepository alunoRepository,
                                CursoRepository cursoRepository,
                                AlunoMapper alunoMapper,
                                CursoMapper cursoMapper) {
        this.matriculaRepository = matriculaRepository;
        this.matriculaMapper = matriculaMapper;
        this.alunoRepository = alunoRepository;
        this.cursoRepository = cursoRepository;
        this.alunoMapper = alunoMapper;
        this.cursoMapper = cursoMapper;
    }

    @Override
    public MatriculaDto cadastraMatricula(Long aluno, Long curso) {
        if(aluno == null || curso == null){
            throw new IllegalArgumentException("Entrada invalida");
        }

        Aluno alunoEncontrado = alunoRepository.findById(aluno).orElseThrow(()-> new IllegalArgumentException("Aluno com o id inexistente"));
        Curso cursoEncontrado = cursoRepository.findById(curso).orElseThrow(() -> new IllegalArgumentException("Curso com o id inexistente"));

        Matricula matriculaNovo = new Matricula();
        matriculaNovo.setAluno(alunoEncontrado);
        matriculaNovo.setCurso(cursoEncontrado);
        matriculaNovo.setDataDeInscricao(LocalDate.now());
        matriculaNovo.setStatus(true);
        return matriculaMapper.toDto(matriculaRepository.save(matriculaNovo));

    }

    @Override
    public List<MatriculaDto> buscaTodasAsMatriculas(){
        return matriculaMapper.toDtoList(matriculaRepository.findAll());
    }

    @Override
    public MatriculaDto buscaMatriculaPorId(Long id){
        if(id == null){
            throw new IllegalArgumentException("Entrada invalida");
        }
        Matricula matriculaLocalizada = matriculaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Matricula com o id inexistente"));
        return matriculaMapper.toDto(matriculaLocalizada);
    }

    @Override
    public MatriculaDto atualizaMatricula(Long matriculaId, MatriculaDto matricula){
        if(matriculaId == null){
            throw new IllegalArgumentException("Entrada invalida");
        }
        if(matricula == null){
            throw new IllegalArgumentException("Entrada invalida");
        }
        Matricula matriculaLocalizada = matriculaRepository.findById(matriculaId).orElseThrow(() -> new
                IllegalArgumentException("Matricula com o id inexistente"));

        if(!matriculaLocalizada.isStatus()){
            throw new RuntimeException("Matricula precisa esta ativa");
        }
        matriculaMapper.updateEntityFromDto(matriculaLocalizada, matricula);
        matriculaRepository.save(matriculaLocalizada);
        return matriculaMapper.toDto(matriculaLocalizada);
    }

    @Override
    public void removeMatricula(Long id) {
        Matricula matriculaLocalizada = matriculaRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("Matricula inexistente"));
        matriculaRepository.delete(matriculaLocalizada);
    }













}
