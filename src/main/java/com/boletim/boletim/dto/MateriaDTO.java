package com.boletim.boletim.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@JsonIgnoreProperties
public class MateriaDTO {

    private Long id;
    private String nome;
    private ProfessorDTO professor;

}
