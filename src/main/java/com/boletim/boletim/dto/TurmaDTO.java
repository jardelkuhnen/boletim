package com.boletim.boletim.dto;

import com.boletim.boletim.model.Turma;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaDTO {

    private Long id;
    @NotEmpty(message = "Nome Não pode ser vazio!")
    private String nome;
    private List<AlunoDTO> alunos = new ArrayList<>();

    public static Turma toTurma(TurmaDTO turmaDTO) {
        Turma turma = new Turma();
        turma.setId(turmaDTO.getId());
        turma.setNome(turmaDTO.getNome());

        return turma;
    }

    public static TurmaDTO toTurmaDTO(Turma turma) {
        return TurmaDTO.builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .alunos(AlunoDTO.toAlunosDTOList(turma.getAlunosTurma())).build();
    }
}
