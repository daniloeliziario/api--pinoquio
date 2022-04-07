package com.eeifpinoquio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Boletim;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long> {
	
	Optional<Boletim> findByAluno(Long aluno);

}
