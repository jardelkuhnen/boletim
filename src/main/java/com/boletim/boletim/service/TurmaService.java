package com.boletim.boletim.service;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.AlunoTurma;
import com.boletim.boletim.model.Professor;
import com.boletim.boletim.model.Turma;
import com.boletim.boletim.repository.AlunoTurmaRepository;
import com.boletim.boletim.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Turma findById(Long id) throws NotFoundException {
        Optional<Turma> turmaOptional = this.turmaRepository.findById(id);

        if (!turmaOptional.isPresent()) {
           throw new NotFoundException("Turma não encontrada!");
        }

        return turmaOptional.get();
    }

    @Transactional
    public TurmaDTO save(TurmaDTO turmaDTO) throws NotFoundException {

        Professor professor = this.professorService.findById(turmaDTO.getId());
        List<AlunoTurma> alunosTurma = createAlunosTurma(turmaDTO.getAlunos());

        Turma turma = new Turma(turmaDTO.getNome(), alunosTurma, professor);

        this.turmaRepository.save(turma);
        this.alunoTurmaRepository.saveAll(alunosTurma);

        return turmaDTO;
    }

    private List<AlunoTurma> createAlunosTurma(List<AlunoDTO> alunos) {
        List<AlunoTurma> alunosTurma = new ArrayList<>();
        for (AlunoDTO alunoDTO: alunos) {
            alunosTurma.add(new AlunoTurma(AlunoDTO.toAluno(alunoDTO), TurmaDTO.toTurma(alunoDTO.getTurma())));
        }
        return  alunosTurma;
    }
}
