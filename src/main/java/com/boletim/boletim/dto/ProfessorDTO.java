package com.boletim.boletim.dto;

import com.boletim.boletim.model.Professor;
import com.boletim.boletim.model.Turma;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class ProfessorDTO {

    private Long id;
    @NotEmpty(message = "Nome não pode ser vazio!")
    private String nome;
    private String sobreNome;
    private TurmaDTO turma = new TurmaDTO();

    public static Professor toProfessor(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setId(professorDTO.getId());
        professor.setNome(professorDTO.getNome());
        professor.setSobreNome(professorDTO.getSobreNome());
        professor.setTurma(TurmaDTO.toTurma(professorDTO.getTurma()));

        return professor;
    }

    public static ProfessorDTO toProfessorDTO(Professor professor) {
        return ProfessorDTO.builder().id(professor.getId()).nome(professor.getNome()).sobreNome(professor.getSobreNome()).turma(TurmaDTO.toTurmaDTO(professor.getTurma())).build();
    }
}
