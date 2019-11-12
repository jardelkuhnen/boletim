package com.boletim.boletim.service;

import com.boletim.boletim.dto.AlunoDTO;
import com.boletim.boletim.dto.TurmaDTO;
import com.boletim.boletim.model.Aluno;
import com.boletim.boletim.repository.AlunoRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO save(AlunoDTO alunoDTO) {
        alunoDTO = AlunoDTO.toAlunoDTO(this.alunoRepository.save(AlunoDTO.toAluno(alunoDTO)));

        return alunoDTO;
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

        if(!alunoOptional.isPresent()) {
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
