package com.boletim.boletim.controller;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.service.AlunoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping
    public ResponseEntity save(@Valid @RequestBody AlunoDTO alunoDTO) throws NotFoundException {
        alunoDTO = this.alunoService.save(alunoDTO);

        return ResponseEntity.ok(alunoDTO);
    }

    @GetMapping
    public ResponseEntity listAll() {
        return ResponseEntity.ok(this.alunoService.listAll());
    }

    @DeleteMapping
    public ResponseEntity delete(@PathVariable Long alunoId) throws NotFoundException {
        this.alunoService.delete(alunoId);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody AlunoDTO alunoDTO) throws NotFoundException {
        alunoDTO = this.alunoService.update(alunoDTO);

        return ResponseEntity.ok(alunoDTO);

    }

}
