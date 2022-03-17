package com.eeifpinoquio.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Aluno;
import com.eeifpinoquio.domain.model.Boletim;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long> {
	
	Optional<Boletim> findByAluno(Aluno aluno);
	
	@Query("from Boletim b where b.aluno.nome like :aluno")
	List<Boletim> findBoletimAluno(String aluno);

}
