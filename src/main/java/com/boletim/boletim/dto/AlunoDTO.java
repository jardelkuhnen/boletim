package com.boletim.boletim.dto;

import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.model.AlunoTurma;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class AlunoDTO {

    private Long id;
    @NotEmpty(message = "RA deve ser informado!")
    private String ra;
    @NotEmpty(message = "Nome não deve ser vazio!")
    private String nome;
    private String sobreNome;
    @NotNull
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
