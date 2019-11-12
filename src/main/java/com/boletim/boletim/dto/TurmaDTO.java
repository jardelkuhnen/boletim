package com.boletim.boletim.dto;

import com.boletim.boletim.model.Turma;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class TurmaDTO {

    private Long id;
    private String nome;
    private List<AlunoDTO> alunos;
    private ProfessorDTO professorDTO;


    public static Turma toTurma(TurmaDTO turmaDTO) {
        Turma turma = new Turma();
        turma.setId(turmaDTO.getId());
        turma.setNome(turmaDTO.getNome());
        turma.setProfessor(ProfessorDTO.toProfessor(turmaDTO.getProfessorDTO()));

        return turma;
    }

    public static TurmaDTO toTurmaDTO(Turma turma) {
        return TurmaDTO.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .alunos(AlunoDTO.toAlunosDTOList(turma.getAlunosTurma()))
                .professorDTO(ProfessorDTO.toProfessorDTO(turma.getProfessor())).build();
    }
}
