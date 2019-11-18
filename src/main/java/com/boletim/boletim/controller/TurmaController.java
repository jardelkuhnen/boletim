package com.boletim.boletim.controller;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.service.TurmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody TurmaDTO turmaDTO) throws NotFoundException {
        turmaDTO = this.turmaService.save(turmaDTO);

        return ResponseEntity.ok(turmaDTO);
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable) {
        return ResponseEntity.ok(this.turmaService.findAll(pageable));
    }

    @GetMapping("/{turmaId}")
    public ResponseEntity getById(@PathVariable Long turmaId) throws NotFoundException {
        return ResponseEntity.ok(this.turmaService.findById(turmaId));
    }

    @DeleteMapping("/{turmaId}")
    public ResponseEntity delete(@PathVariable Long turmaId) throws NotFoundException {
        this.turmaService.delete(turmaId);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TurmaDTO turmaDTO) throws NotFoundException {
        turmaDTO = this.turmaService.update(turmaDTO);

        return ResponseEntity.ok(turmaDTO);

    }

}
