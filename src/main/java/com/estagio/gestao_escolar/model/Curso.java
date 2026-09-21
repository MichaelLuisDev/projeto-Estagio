package com.estagio.gestao_escolar.model;

// import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "descrição")
    private String descricao;

    @Column(name = "ativo")
    private boolean statusAtivo;

    // Muitos cursos pertencem a um único professor
    // Cria fisicamente a coluna 'professor_id' na tabela de cursos
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false) // nullable = false garante que todo curso precisa ter um professor
    private Professor professor;

    public Curso() {

    }

    public Curso(long id, String nome, String descricao, boolean statusAtivo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.statusAtivo = statusAtivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
}
