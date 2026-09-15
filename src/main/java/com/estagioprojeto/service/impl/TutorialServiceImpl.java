package com.estagioprojeto.service.impl;

import com.estagioprojeto.dto.TutorialDto;
import com.estagioprojeto.mapper.TutorialMapper;
import com.estagioprojeto.model.Tutorial;
import com.estagioprojeto.repository.TutorialRepository;
import com.estagioprojeto.service.TutorialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;
    private final TutorialMapper tutorialMapper;

    public TutorialServiceImpl(TutorialRepository tutorialRepository, TutorialMapper tutorialMapper) {
        this.tutorialRepository = tutorialRepository;
        this.tutorialMapper = tutorialMapper;
    }
    //DTO
    @Override
    public List<TutorialDto> findPorPublicacao(boolean opcao) {
        List<TutorialDto> respTutorials = tutorialMapper.toDtoList(tutorialRepository.findByPublished(opcao));

        if (respTutorials.isEmpty()) {
            return respTutorials;
        }
        return respTutorials;
    }

    @Override
    public void deletarPorId(long id) {
        if (!tutorialRepository.existsById(id)) {
            throw new EntityNotFoundException("Tutorial não encontrado com o ID: " + id);
        }
        tutorialRepository.deleteById(id);
    }

    //DTO
    @Override
    public List<TutorialDto> chamarTodosPorDescricao(String description) {
        return tutorialMapper.toDtoList(tutorialRepository.findAllByDescription(description));
    }
    //DTO
    @Override
    public TutorialDto atualizarPorId(long id, TutorialDto tutorialDto) {
        try {
            if(tutorialRepository.existsById(id)){
                tutorialMapper.updateEntityFromDto(tutorialRepository.findById(id).get(), tutorialDto);
                return tutorialMapper.toDto(tutorialRepository.save(tutorialRepository.findById(id).get()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //DTO
    @Override
    public TutorialDto criarTutorial(TutorialDto tutorial){
        Tutorial tutorialEntity = tutorialMapper.toEntity(tutorial);
        Tutorial tutorialSalvo = tutorialRepository.save(new Tutorial(tutorialEntity.getTitle(),
                tutorialEntity.getDescription(),
                true));

        try{
            return tutorialMapper.toDto(tutorialSalvo);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    //DTO
    @Override
    public TutorialDto localizarPorId(long id){
        try {
            if (tutorialRepository.findById(id).isPresent()) {
                TutorialDto tutorialLocalizado = tutorialMapper.toDto(tutorialRepository.findById(id).get());
                return tutorialLocalizado;
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return null;
    }
    //DTO
    @Override
    public List<TutorialDto> localizarTodos(String title){
        //List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            return tutorialMapper.toDtoList(tutorialRepository.findAll());
        }
        else {
            return tutorialMapper.toDtoList(tutorialRepository.findByTitleContaining(title));
        }
    }





}

