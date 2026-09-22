package com.estagio.gestao_escolar.controller;

import com.estagio.gestao_escolar.dto.AlunoDto;
import com.estagio.gestao_escolar.dto.CursoDto;
import com.estagio.gestao_escolar.dto.ProfessorDto;
import com.estagio.gestao_escolar.repository.AlunoRepository;
import com.estagio.gestao_escolar.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/aluno")
    public ResponseEntity<AlunoDto> createAluno(@RequestBody AlunoDto aluno) {
        AlunoDto novoAluno = alunoService.cadastraAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @GetMapping("/aluno")
    public ResponseEntity<List<AlunoDto>> getAllAlunos() {
        List<AlunoDto> alunos = alunoService.buscaTodosOsAlunos();
        if (alunos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }

    @GetMapping("/aluno/{id}")
    public ResponseEntity<AlunoDto> getAlunoById(@PathVariable("id") Long id) {
        AlunoDto aluno = alunoService.buscaAlunoPorId(id);
        return ResponseEntity.ok(aluno);
    }

    @PutMapping("/aluno/{id}")
    public ResponseEntity<AlunoDto> updateAluno(@PathVariable("id") long id, @RequestBody AlunoDto aluno) {
        AlunoDto alunoAtualizado = alunoService.atualizaCadastroAluno(id, aluno);
        return ResponseEntity.ok(alunoAtualizado);
    }


    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<Void> deleteAluno(@PathVariable("id") long id) {
        alunoService.removeAluno(id);
        return ResponseEntity.noContent().build();
    }
}
