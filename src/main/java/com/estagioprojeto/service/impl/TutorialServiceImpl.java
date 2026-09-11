package com.estagioprojeto.service.impl;

import com.estagioprojeto.model.Tutorial;
import com.estagioprojeto.repository.TutorialRepository;
import com.estagioprojeto.service.TutorialService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TutorialServiceImpl implements TutorialService {

    private final TutorialRepository tutorialRepository;

    public TutorialServiceImpl(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    @Override
    public List<Tutorial> findPorPublicacao(boolean opcao) {
        List<Tutorial> respTutorials = tutorialRepository.findByPublished(opcao);
        if (respTutorials.isEmpty()) {
            return respTutorials;
        }
        return respTutorials;
    }
    /*public void deletarTudo(){
        tutorialRepository.deleteAll();
    }*/
    @Override
    public void deletarPorId(long id){
       tutorialRepository.deleteById(id);
    }
    @Override
    public List<Tutorial> chamarTodosPorDescricao(String description){
        return tutorialRepository.findAllByDescription(description);
    }




}
