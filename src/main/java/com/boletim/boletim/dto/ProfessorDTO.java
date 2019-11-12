package com.boletim.boletim.dto;

import com.boletim.boletim.model.Professor;
import com.boletim.boletim.model.Turma;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorDTO {

    private Long id;
    private String nome;
    private String sobreNome;
    private TurmaDTO turma;

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
