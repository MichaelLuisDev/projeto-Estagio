package com.estagioprojeto.controller;

import com.estagioprojeto.dto.TutorialDto;
import com.estagioprojeto.model.Tutorial;
import com.estagioprojeto.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estagioprojeto.repository.TutorialRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @Autowired
    TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<TutorialDto>> getAllTutorials(@RequestParam(required = false) String title) {
        try {
            List<TutorialDto> tutorials = tutorialService.localizarTodos(title);

            if (tutorials.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<TutorialDto> getTutorialById(@PathVariable("id") long id) {
        TutorialDto tutorialData = tutorialService.localizarPorId(id);
        if(tutorialData!=null){
            return new ResponseEntity<>(tutorialData, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PostMapping("/tutorials")
    public ResponseEntity<TutorialDto> createTutorial(@RequestBody TutorialDto tutorial) {
        try {
            TutorialDto _tutorial = tutorialService.criarTutorial(tutorial);
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<TutorialDto> updateTutorial(@PathVariable("id") long id, @RequestBody TutorialDto tutorial) {
        TutorialDto tutorialAtualizado = tutorialService.atualizarPorId(id, tutorial);
        if(tutorialAtualizado!=null) {
            return new ResponseEntity<>(tutorialAtualizado, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
        try {
            tutorialService.deletarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<TutorialDto>> findByPublished(boolean isPublished) {
        try {
            List<TutorialDto> tutorials = tutorialService.findPorPublicacao(isPublished);
            return ResponseEntity.ok(tutorials);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/tutorials/chamar-todos")
    public ResponseEntity<List<TutorialDto>> chamarTodosPorDescription(@RequestParam(name = "description") String description) {
        try {
            List<TutorialDto> tutorials = tutorialService.chamarTodosPorDescricao(description);
            return ResponseEntity.ok(tutorials);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
