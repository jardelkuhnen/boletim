package com.boletim.boletim.service;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.AlunoTurmaDTO;
import com.boletim.boletim.dto.ProfessorDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.AlunoTurma;
import com.boletim.boletim.model.Professor;
import com.boletim.boletim.model.Turma;
import com.boletim.boletim.repository.AlunoTurmaRepository;
import com.boletim.boletim.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorService professorService;
    private final AlunoTurmaRepository alunoTurmaRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, ProfessorService professorService, AlunoTurmaRepository alunoTurmaRepository) {
        this.turmaRepository = turmaRepository;
        this.professorService = professorService;
        this.alunoTurmaRepository = alunoTurmaRepository;
    }

    public TurmaDTO findById(Long id) throws NotFoundException {
        Optional<Turma> turmaOptional = this.turmaRepository.findById(id);

        if (!turmaOptional.isPresent()) {
           throw new NotFoundException("Turma não encontrada!");
        }

        Turma turma = turmaOptional.get();
        List<AlunoTurma> alunos = this.alunoTurmaRepository.findByTurmaId(turma.getId());
        turma.setAlunosTurma(alunos);

        return TurmaDTO.of(turmaOptional.get());
    }

    @Transactional
    public TurmaDTO save(TurmaDTO turmaDTO) throws NotFoundException {
        Professor professor = null;
        Turma turma = new Turma(turmaDTO.getNome());

        if (turmaDTO.getProfessorDTO() != null && turmaDTO.getProfessorDTO().getId() != null) {
            professor = this.professorService.findById(turmaDTO.getProfessorDTO().getId());
            turma.setProfessor(professor);
        }

        turma = this.turmaRepository.save(turma);
        professor.setTurma(turma);
        this.professorService.save(ProfessorDTO.of(professor));

        if(!turmaDTO.getAlunos().isEmpty()) {
            List<AlunoTurma> alunosTurma = createAlunosTurma(turmaDTO.getAlunos(), turma);
            alunosTurma = this.alunoTurmaRepository.saveAll(alunosTurma);
            turma.setAlunosTurma(alunosTurma);
        }

        turma = this.turmaRepository.save(turma);
        return TurmaDTO.of(turma);
    }

    private List<AlunoTurma> createAlunosTurma(List<AlunoDTO> alunos, Turma turma) {
        List<AlunoTurma> alunosTurma = new ArrayList<>();

        for (AlunoDTO alunoDTO: alunos) {
            alunosTurma.add(AlunoTurmaDTO.of(alunoDTO, turma));
        }

        return  alunosTurma;
    }

    public Page<TurmaDTO> findAll(Pageable pageable) {
        Page<TurmaDTO> turmas = this.turmaRepository.findAll(pageable).map(TurmaDTO::of);
        return turmas;
    }

    public void delete(Long turmaId) throws NotFoundException {
        Optional<Turma> turmaOptional = this.turmaRepository.findById(turmaId);

        if(!turmaOptional.isPresent()) {
            throw new NotFoundException("Turma não encontrada!");
        }

        this.turmaRepository.delete(turmaOptional.get());

    }

    public TurmaDTO update(TurmaDTO turmaDTO) throws NotFoundException {
        Optional<Turma> turmaOptional = this.turmaRepository.findById(turmaDTO.getId());

        if(!turmaOptional.isPresent()) {
            throw new NotFoundException("Turma não encontrada!");
        }

        Turma turma = turmaOptional.get();
        turma.setNome(turmaDTO.getNome());
        return TurmaDTO.of(this.turmaRepository.save(turma));
    }
}
