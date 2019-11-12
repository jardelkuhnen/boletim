package com.boletim.boletim.dto;

import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.model.AlunoTurma;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class AlunoDTO {

    private Long id;
    private String ra;
    private String nome;
    private String sobreNome;
    private TurmaDTO turma;

    public static Aluno toAluno(AlunoDTO alunoDTO) {
        Aluno aluno = new Aluno();
        aluno.setNome(alunoDTO.getNome());
        aluno.setRa(alunoDTO.getRa());
        aluno.setSobreNome(alunoDTO.getSobreNome());
        aluno.setTurma(TurmaDTO.toTurma(alunoDTO.getTurma()));

        return aluno;
    }

    public static AlunoDTO toAlunoDTO(Aluno aluno) {
        return AlunoDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .sobreNome(aluno.getSobreNome())
                .turma(TurmaDTO.toTurmaDTO(aluno.getTurma())).build();
    }

    public static List<AlunoDTO> toAlunosDTOList(List<AlunoTurma> alunosTurma) {
        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        for (AlunoTurma alunoTurma: alunosTurma) {
            alunoDTOS.add(AlunoDTO.toAlunoDTO(alunoTurma.getAluno()));
        }

        return alunoDTOS;
    }


    public static List<AlunoDTO> toAlunosDTOListFromALunos(List<Aluno> alunos) {
        List<AlunoDTO> alunoDTOS = new ArrayList<>();

        for (Aluno aluno: alunos) {
            alunoDTOS.add(AlunoDTO.toAlunoDTO(aluno));
        }

        return alunoDTOS;
    }

}
