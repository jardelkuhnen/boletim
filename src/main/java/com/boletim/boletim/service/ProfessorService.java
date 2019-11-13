package com.boletim.boletim.service;

import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.Professor;
import com.boletim.boletim.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor findById(Long id) throws NotFoundException {
        Optional<Professor> professorOptional = this.professorRepository.findById(id);

        if (!professorOptional.isPresent()) {
           throw new NotFoundException("Professor não encontrado!");
        }

        return professorOptional.get();
    }

    public ProfessorDTO save(ProfessorDTO professorDTO) {
        Professor professor = new Professor(professorDTO.getNome(), professorDTO.getSobreNome(), professorDTO.getTurma());

        this.professorRepository.save(professor);

        return professorDTO;
    }
}
