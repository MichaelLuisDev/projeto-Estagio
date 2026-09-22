package com.estagio.gestao_escolar.model;

// import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "descrição")
    private String descricao;

    @Column(name = "ativo")
    private boolean statusAtivo;

    // Muitos cursos pertencem a um único professor
    // Cria fisicamente a coluna 'professor_id' na tabela de cursos
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Matricula> matriculas = new ArrayList<>();

    public Curso() {

    }

    public Curso(Long id, String nome, String descricao, boolean statusAtivo, Professor professor, List<Matricula> matriculas) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.statusAtivo = statusAtivo;
        this.professor = professor;
        this.matriculas = matriculas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }
}
