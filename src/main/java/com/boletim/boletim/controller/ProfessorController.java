package com.boletim.boletim.controller;

import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ProfessorDTO professorDTO) {
        this.professorService.save(professorDTO);

        return ResponseEntity.ok(professorDTO);
    }



}
