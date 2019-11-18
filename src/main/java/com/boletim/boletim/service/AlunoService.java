package com.boletim.boletim.service;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.exception.NotFoundException;
import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

        Aluno aluno = new Aluno(alunoDTO.getRa(), alunoDTO.getNome(), alunoDTO.getSobreNome());

        if (alunoDTO.getTurma() != null && alunoDTO.getTurma().getId() != null) {
           TurmaDTO turmaDTO = this.turmaService.findById(alunoDTO.getTurma().getId());
            aluno.setTurma(TurmaDTO.of(turmaDTO));
        }

        aluno = this.alunoRepository.save(aluno);

        return AlunoDTO.toAlunoDTO(aluno);
    }

    public Page<AlunoDTO> listAll(Pageable pageable) {
        Page<AlunoDTO> alunos = this.alunoRepository.findAll(pageable).map(AlunoDTO::toAlunoDTO);

        return alunos;
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
        aluno.setSobreNome(alunoDTO.getSobreNome());

        if(alunoDTO.getTurma() != null) {
            aluno.setTurma(TurmaDTO.of(alunoDTO.getTurma()));
        }


        this.alunoRepository.save(aluno);

        return AlunoDTO.toAlunoDTO(aluno);

    }

    public AlunoDTO findById(Long alunoId) throws NotFoundException {
        Optional<Aluno> alunoOptional = this.alunoRepository.findById(alunoId);

        if(!alunoOptional.isPresent()) {
            throw new NotFoundException("Aluno não encontrado!");
        }
        return AlunoDTO.toAlunoDTO(alunoOptional.get());
    }
}
