package com.boletim.boletim.repository;

import com.boletim.boletim.model.AlunoTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoTurmaRepository extends JpaRepository<AlunoTurma, Long> {
}
