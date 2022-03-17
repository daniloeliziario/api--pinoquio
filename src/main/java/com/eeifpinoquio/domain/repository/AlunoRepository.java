package com.eeifpinoquio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	Optional<Aluno> findByNome(String nome);

}
