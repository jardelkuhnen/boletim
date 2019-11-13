package com.boletim.boletim.service;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.model.Turma;
import com.boletim.boletim.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final TurmaService turmaService;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, TurmaService turmaService) {
        this.alunoRepository = alunoRepository;
        this.turmaService = turmaService;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) throws NotFoundException {
        Turma turma = this.turmaService.findById(alunoDTO.getTurma().getId());
        Aluno aluno = new Aluno(alunoDTO.getRa(), alunoDTO.getNome(), alunoDTO.getSobreNome(), turma);

        return AlunoDTO.toAlunoDTO(this.alunoRepository.save(aluno));
    }

    public List<AlunoDTO> listAll() {
        List<Aluno> alunos = this.alunoRepository.findAll();

        List<AlunoDTO> alunoDTOS = AlunoDTO.toAlunosDTOListFromALunos(alunos);

        return alunoDTOS;
    }

    public void delete(Long alunoId) throws NotFoundException {
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);

        if (!alunoOptional.isPresent()) {
            throw new NotFoundException("Aluno não encontrado!");
        }

        this.alunoRepository.delete(alunoOptional.get());
    }

    public AlunoDTO update(AlunoDTO alunoDTO) throws NotFoundException {
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoDTO.getId());

        if (!alunoOptional.isPresent()) {
            throw new NotFoundException("Aluno não encontrado!");
        }

        Aluno aluno = alunoOptional.get();
        aluno.setRa(alunoDTO.getRa());
        aluno.setNome(alunoDTO.getNome());
        aluno.setSobreNome(aluno.getSobreNome());
        aluno.setTurma(TurmaDTO.toTurma(alunoDTO.getTurma()));

        this.alunoRepository.save(aluno);

        return AlunoDTO.toAlunoDTO(aluno);

    }
}
