package com.estagio.gestao_escolar.controller;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.MatriculaDto;
import com.estagio.gestao_escolar.repository.MatriculaRepository;
import com.estagio.gestao_escolar.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class MatriculaController {

    @Autowired
    MatriculaService matriculaService;

    @PostMapping("/matricula")
    public ResponseEntity<MatriculaDto> createMatricula(@RequestParam Long aluno, @RequestParam Long curso) {
        MatriculaDto novoMatricula = matriculaService.cadastraMatricula(aluno, curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoMatricula);
    }
    @GetMapping("/matricula")
    public ResponseEntity<List<MatriculaDto>> getAllMatriculas() {
        List<MatriculaDto> matriculas = matriculaService.buscaTodasAsMatriculas();
        if (matriculas.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(matriculas, HttpStatus.OK);
    }
    @GetMapping("/matricula/{id}")
    public ResponseEntity<MatriculaDto> getMatriculasById(@RequestParam Long id) {
        MatriculaDto matricula = matriculaService.buscaMatriculaPorId(id);
        return ResponseEntity.ok(matricula);
    }
    @PutMapping("/matricula")
    public ResponseEntity<MatriculaDto> updateMatricula(@RequestParam Long id, @RequestBody MatriculaDto matricula) {
        MatriculaDto matriculaAtualizada = matriculaService.atualizaMatricula(id, matricula);
        return ResponseEntity.ok(matriculaAtualizada);
    }
    @DeleteMapping("/matricula")
    public ResponseEntity<MatriculaDto> deleteMatricula(@RequestParam Long id) {
        matriculaService.removeMatricula(id);
        return ResponseEntity.noContent().build();
    }
}
