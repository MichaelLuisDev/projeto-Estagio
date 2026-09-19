package com.estagio.gestao_escolar.controller;

import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.model.Curso;
import com.estagio.gestao_escolar.service.impl.CursoServiceImpl; // ou interface CursoService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CursoController {

    @Autowired
    private CursoServiceImpl cursoService;

    @GetMapping("/cursos")
    public ResponseEntity<List<CursoDto>> getAllCursos() {
        List<CursoDto> cursos = cursoService.retornaListaDeCursos();
        if (cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<CursoDto> getCursoById(@PathVariable("id") long id) {
        CursoDto curso = cursoService.retornaCursoPorId(id);
        return ResponseEntity.ok(curso);
    }

    @PostMapping("/cursos")
    public ResponseEntity<CursoDto> createCurso(@RequestBody CursoDto curso) {
        CursoDto novoCurso = cursoService.cadastraNovoCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @PutMapping("/cursos/{id}")
    public ResponseEntity<CursoDto> updateCurso(@PathVariable("id") long id, @RequestBody CursoDto curso) {
        CursoDto cursoAtualizado = cursoService.atualizaCurso(id, curso);
        return ResponseEntity.ok(cursoAtualizado);
    }

    @DeleteMapping("/cursos/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable("id") long id) {
        cursoService.deletaCurso(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cursos/ativos") // Convenção: letras minúsculas
    public ResponseEntity<List<CursoDto>> findByStatusAtivo(boolean status) {
        List<CursoDto> cursos = cursoService.retornaListaDeCursosPorStatus(status);
        if (cursos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }
}