package com.boletim.boletim.dto;

import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.model.AlunoTurma;
import com.boletim.boletim.model.Turma;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoTurmaDTO {
    private Long id;

    private AlunoDTO alunoDTO;

    private TurmaDTO turmaDTO;

    public static AlunoTurma of(AlunoDTO alunoDTO, Turma turma) {
        AlunoTurma alunoTurma = new AlunoTurma();
        alunoTurma.setAluno(AlunoDTO.of(alunoDTO));
        alunoTurma.setTurma(turma);

        return alunoTurma;
    }

    public static List<AlunoTurma> of(List<AlunoDTO> alunos) {
        List<AlunoTurma> alunosTurma = new ArrayList<>();
        for (AlunoDTO alunoDTO: alunos) {
            Aluno aluno = AlunoDTO.of(alunoDTO);
            Turma turma = TurmaDTO.of(alunoDTO.getTurma());

            alunosTurma.add(new AlunoTurma(aluno, turma));
        }

        return alunosTurma;
    }
}
