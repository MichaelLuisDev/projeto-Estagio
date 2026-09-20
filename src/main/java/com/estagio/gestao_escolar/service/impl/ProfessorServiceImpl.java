package com.estagio.gestao_escolar.service.impl;


import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.mapper.ProfessorMapper;
import com.estagio.gestao_escolar.model.Professor;
import com.estagio.gestao_escolar.repository.ProfessorRepository;
import com.estagio.gestao_escolar.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorServiceImpl(ProfessorRepository professorRepository, ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }
    // retorna todos os professores existente
    @Override
    public List<ProfessorDto> retornaTodosOsProfessores() {
        return professorMapper.toDtoList(professorRepository.findAll());
    }
    //retorna professor por ID
    @Override
    public ProfessorDto retornaProfessorPorId(long id) {
        return professorMapper.toDto(professorRepository.findById(id).
                orElseThrow(()-> new EntityNotFoundException("Professor com o ID: "+id+" não encontrado")));
    }
    //retorna professor por nome
    @Override
    public List<ProfessorDto> retornaProfessorPorNome(String nome){
       List<ProfessorDto> professorLocalizado = professorMapper.toDtoList(professorRepository.encontrarPorNome(nome));
       if(!professorLocalizado.isEmpty()){
           return professorLocalizado;
       }else{
           throw new EntityNotFoundException("Professor com o nome: "+nome+"Não encontrado");
       }
    }
    //retorna professor por especialidade
    @Override
    public List<ProfessorDto> retornaProfessorPorEspecialidade(String especialidade){
       List<ProfessorDto> professorLocalizado  = professorMapper.toDtoList(professorRepository.encontrarPorEspecialidade(especialidade));
        if(!professorLocalizado.isEmpty()){
            return professorLocalizado;
        }else{
            throw new EntityNotFoundException("Professor com o nome: "+especialidade+"Não encontrado");
        }


    }
    //cadastra um novo professor
    @Override
    public ProfessorDto cadastraProfessor(ProfessorDto professor){
        if(professor!=null){
            return professorMapper.toDto(professorRepository.save(professorMapper.toEntity(professor)));
        }else{
            throw new IllegalArgumentException("Entrada inválida");
        }


    }
    //atualiza o registro de um professor existente
    @Override
    public ProfessorDto atualizaProfessor(long id, ProfessorDto professorAtualizado){
        Professor professorExistente = professorMapper.toEntity(retornaProfessorPorId(id));
        professorExistente.setNome(professorAtualizado.professor_Nome());
        professorExistente.setEspecialidade((professorAtualizado.especialidade()));

        return professorMapper.toDto(professorRepository.save(professorExistente));

    }
    //deleta um professor existente
    @Override
    public void deletaProfessor(long id){
        if (!professorRepository.existsById(id)) {
            throw new EntityNotFoundException("Professor com ID " + id + " não encontrado para exclusão.");
        }
        professorRepository.deleteById(id);
    }














}
