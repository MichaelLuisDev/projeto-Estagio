package com.estagio.gestao_escolar.model;

// import javax.persistence.*; // for Spring Boot 2
import jakarta.persistence.*; // for Spring Boot 3

@Entity
@Table(name = "Curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nome do curso")
    private String nomeCurso;

    @Column(name = "descrição")
    private String descricaoCurso;

    @Column(name = "esta ativo?")
    private boolean statusAtivo;

    public Curso() {
    }
    public Curso(String nomeCurso, String descricaoCurso, boolean statusAtivo) {
        this.nomeCurso = nomeCurso;
        this.descricaoCurso = descricaoCurso;
        this.statusAtivo = statusAtivo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getDescricaoCurso() {
        return descricaoCurso;
    }

    public void setDescricaoCurso(String descricaoCurso) {
        this.descricaoCurso = descricaoCurso;
    }

    public boolean isStatusAtivo() {
        return statusAtivo;
    }

    public void setStatusAtivo(boolean statusAtivo) {
        this.statusAtivo = statusAtivo;
    }
}
