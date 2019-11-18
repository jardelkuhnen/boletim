package com.boletim.boletim.dto;

import com.boletim.boletim.model.Turma;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {

    private Long id;
    @NotEmpty(message = "Nome Não pode ser vazio!")
    private String nome;
    private ProfessorDTO professorDTO;
    private List<AlunoDTO> alunos = new ArrayList<>();

    public static Turma of(TurmaDTO turmaDTO) {
        Turma turma = new Turma();
        turma.setId(turmaDTO.getId());
        turma.setNome(turmaDTO.getNome());
        turma.setProfessor(ProfessorDTO.of(turmaDTO.getProfessorDTO()));
        turma.setAlunosTurma(AlunoTurmaDTO.of(turmaDTO.getAlunos()));

        return turma;
    }

    public static TurmaDTO of(Turma turma) {
        if (turma == null) {
           return null;
        }
        return TurmaDTO.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .professorDTO(ProfessorDTO.of(turma.getProfessor()))
                .alunos(AlunoDTO.toAlunosDTOList(turma.getAlunosTurma())).build();
    }
}
