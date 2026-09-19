package com.estagio.gestao_escolar.controller;

import com.estagio.gestao_escolar.model.Curso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estagio.gestao_escolar.repository.CursoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CursoController {

    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("/Cursos")
    public ResponseEntity<List<Curso>> getAllCursos(@RequestParam(required = false) String nomeCurso) {
        try {
            List<Curso> cursos = new ArrayList<Curso>();

            if (nomeCurso == null)
                cursoRepository.findAll().forEach(cursos::add);
            else
                cursos.addAll(cursoRepository.findByNomeCursoContaining(nomeCurso));

            if (cursos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Cursos/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable("id") long id) {
        Optional<Curso> cursoData = cursoRepository.findById(id);

        if (cursoData.isPresent()) {
            return new ResponseEntity<>(cursoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Cursos")
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        try {
            Curso _curso = cursoRepository
                    .save(new Curso(curso.getNomeCurso(), curso.getDescricaoCurso(), false));
            return new ResponseEntity<>(_curso, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Cursos/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable("id") long id, @RequestBody Curso curso) {
        Optional<Curso> cursoData = cursoRepository.findById(id);

        if (cursoData.isPresent()) {
            Curso cursoAtualizado = cursoData.get();
            cursoAtualizado.setNomeCurso(curso.getNomeCurso());
            cursoAtualizado.setDescricaoCurso(curso.getDescricaoCurso());
            return new ResponseEntity<>(cursoRepository.save(cursoAtualizado), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Cursos/{id}")
    public ResponseEntity<HttpStatus> deleteCurso(@PathVariable("id") long id) {
        try {
            cursoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Cursos/Ativos")
    public ResponseEntity<List<Curso>> findByStatusAtivo() {
        try {
            List<Curso> cursos = cursoRepository.findByStatusAtivo(true);

            if (cursos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cursos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
