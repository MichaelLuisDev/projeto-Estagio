package com.estagio.gestao_escolar.controller;

import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/professor")
    public ResponseEntity<List<ProfessorDto>> getAllProfessor() {
        List<ProfessorDto> professor = professorService.retornaTodosOsProfessores();
        if (professor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }
    @GetMapping("/professor/{nome}")
    public ResponseEntity<List<ProfessorDto>> getAllProfessorByNome(@PathVariable String nome) {
        List<ProfessorDto> professor = professorService.retornaProfessorPorNome(nome);
        if (professor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }
    @GetMapping("/professor/{especialidade}")
    public ResponseEntity<List<ProfessorDto>> getAllProfessorBySpecialised(@PathVariable String especialidade) {
        List<ProfessorDto> professor = professorService.retornaProfessorPorEspecialidade(especialidade);
        if (professor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }
    @GetMapping("/professor/{id}")
    public ResponseEntity<ProfessorDto> getProfessorById(@PathVariable("id") long id) {
        ProfessorDto professor = professorService.retornaProfessorPorId(id);
        return ResponseEntity.ok(professor);
    }
    @PostMapping("/professor")
    public ResponseEntity<ProfessorDto> createProfessor(@RequestBody ProfessorDto professor) {
        ProfessorDto novoProfessor = professorService.cadastraProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProfessor);
    }
    @PutMapping("/professor/{id}")
    public ResponseEntity<ProfessorDto> updateProfessor(@PathVariable("id") long id, @RequestBody ProfessorDto professor) {
        ProfessorDto professorAtualizado = professorService.atualizaProfessor(id, professor);
        return ResponseEntity.ok(professorAtualizado);
    }
    @DeleteMapping("/professor/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable("id") long id) {
       professorService.deletaProfessor(id);
        return ResponseEntity.noContent().build();
    }














}
