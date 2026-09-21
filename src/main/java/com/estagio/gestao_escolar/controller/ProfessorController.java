package com.estagio.gestao_escolar.controller;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class ProfessorController {
    @Autowired
    private ProfessorService professorService;

    @GetMapping("/professor")
    public ResponseEntity<List<ProfessorDto>> getAllProfessores() {
        List<ProfessorDto> professores = professorService.retornaListaDeProfessores();
        if (professores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(professores, HttpStatus.OK);
    }

    @PostMapping("/professor")
    public ResponseEntity<ProfessorDto> createProfessor(@RequestParam(required = false) Long cursoId, @RequestBody ProfessorDto professor) {
        ProfessorDto novoProfessor = professorService.cadastraProfessor(cursoId, professor);
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
