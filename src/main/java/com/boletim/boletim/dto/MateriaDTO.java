package com.boletim.boletim.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MateriaDTO {

    private Long id;
    private String nome;
    private ProfessorDTO professor;

}
