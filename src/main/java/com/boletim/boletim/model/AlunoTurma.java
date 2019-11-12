package com.boletim.boletim.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "aluno_turma")
public class AlunoTurma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne()
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    private Turma turma;
}
