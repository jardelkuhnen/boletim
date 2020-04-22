package com.boletim.boletim.controller;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/professor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ProfessorDTO professorDTO) {
        professorDTO = this.professorService.save(professorDTO);

        return ResponseEntity.ok(professorDTO);
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {
        return ResponseEntity.ok(this.professorService.findAll(pageable));
    }

    @GetMapping("/{professorId}")
    public ResponseEntity getById(@PathVariable Long professorId) throws NotFoundException {
        return ResponseEntity.ok(this.professorService.findById(professorId));
    }

    @DeleteMapping("/{professorId}")
    public ResponseEntity delete(@PathVariable Long professorId) throws NotFoundException {
        this.professorService.delete(professorId);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody ProfessorDTO professorDTO) throws NotFoundException {
        professorDTO = this.professorService.update(professorDTO);

        return ResponseEntity.ok(professorDTO);

    }


}
