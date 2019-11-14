package com.boletim.boletim.model;

import com.boletim.boletim.dto.TurmaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "sobre_nome")
    private String sobreNome;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Turma turma;


    public Professor(String nome, String sobreNome) {
        this.nome = nome;
        this.sobreNome = sobreNome;
    }
}
