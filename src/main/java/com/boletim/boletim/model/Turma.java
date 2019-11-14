package com.boletim.boletim.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "turma")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "aluno", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AlunoTurma> alunosTurma = new ArrayList<>();

    @OneToOne(mappedBy = "turma", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Professor professor;

    public Turma(String nome, List<AlunoTurma> alunosTurma, Professor professor) {
        this.nome = nome;
        this.alunosTurma = alunosTurma;
        this.professor = professor;
    }

    public Turma(String nome) {
        this.nome = nome;
    }
}
