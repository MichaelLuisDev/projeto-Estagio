package com.estagioprojeto.service.impl;

import com.estagioprojeto.model.Tutorial;
import com.estagioprojeto.repository.TutorialRepository;
import com.estagioprojeto.service.TutorialService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public void deletarPorId(long id) {
        tutorialRepository.deleteById(id);
    }

    @Override
    public List<Tutorial> chamarTodosPorDescricao(String description) {
        return tutorialRepository.findAllByDescription(description);
    }

    @Override
    public Tutorial atualizarPorId(Tutorial tutorial, long id){
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return tutorialRepository.save(_tutorial);
        }
        return null;
    }

    @Override
    public Tutorial criarTutorial(Tutorial tutorial){
        tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), true));

        try{
            if(tutorial!=null){
                return tutorial;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;

    }

    @Override
    public Optional<Tutorial> localizarPorId(long id){
        if(tutorialRepository.findById(id).isPresent()){
            return  tutorialRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Tutorial> localizarTodos(String title){
        List<Tutorial> tutorials = new ArrayList<Tutorial>();

        if (title == null) {
            tutorials.addAll(tutorialRepository.findAll());
        }
        else {
            tutorials.addAll(tutorialRepository.findByTitleContaining(title));
        }

        return tutorials;
    }





}

