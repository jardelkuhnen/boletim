package com.boletim.boletim.service;

import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.Professor;
import com.boletim.boletim.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Professor professor = new Professor(professorDTO.getNome(), professorDTO.getSobreNome());

        professorDTO = ProfessorDTO.toProfessorDTO(this.professorRepository.save(professor));

        return professorDTO;
    }

    public List<ProfessorDTO> findAll() {
        List<Professor> professores = this.professorRepository.findAll();

        return ProfessorDTO.of(professores);
    }

    public void delete(Long professorId) throws NotFoundException {
        Optional<Professor> professorOptional = this.professorRepository.findById(professorId);

        if (!professorOptional.isPresent()) {
           throw new NotFoundException("Professor nao encontrado!");
        }

        this.professorRepository.delete(professorOptional.get());

    }

    public ProfessorDTO update(ProfessorDTO professorDTO) throws NotFoundException {
        Optional<Professor> professorOptional = this.professorRepository.findById(professorDTO.getId());

        if (!professorOptional.isPresent()) {
            throw new NotFoundException("Professor nao encontrado!");
        }

        Professor professor = professorOptional.get();
        professor.setNome(professorDTO.getNome());
        professor.setSobreNome(professorDTO.getSobreNome());

        return ProfessorDTO.toProfessorDTO(this.professorRepository.save(professor));

    }


}
