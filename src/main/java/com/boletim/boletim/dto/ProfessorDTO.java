package com.boletim.boletim.dto;

import com.boletim.boletim.model.Professor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

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

    public static Professor of(ProfessorDTO professorDTO) {
        Professor professor = new Professor();
        professor.setId(professorDTO.getId());
        professor.setNome(professorDTO.getNome());
        professor.setSobreNome(professorDTO.getSobreNome());

        return professor;
    }

    public static ProfessorDTO of(Professor professor) {
        return ProfessorDTO.builder().id(professor.getId()).nome(professor.getNome()).sobreNome(professor.getSobreNome()).build();
    }

    public static List<ProfessorDTO> of(List<Professor> professores) {
        List<ProfessorDTO> professorDTOS = new ArrayList<>();
        for (Professor professor: professores) {
            professorDTOS.add(ProfessorDTO.of(professor));
        }

        return professorDTOS;
    }
}
