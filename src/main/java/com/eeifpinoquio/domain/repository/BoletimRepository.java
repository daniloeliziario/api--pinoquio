package com.eeifpinoquio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Bimestre;
import com.eeifpinoquio.domain.model.Boletim;

@Repository
public interface BoletimRepository extends JpaRepository<Boletim, Long>, BoletimRepositoryQuery {
	
	Optional<Boletim> findByAluno(Long aluno);
	
	@Query("select bi from Bimestre bi, Boletim b, Disciplina d "
			+ "where b.id = :idBoletim and d.id = :idDisciplina and bi.id = :idBimestre")
	Optional<Bimestre> findBimestreById(Long idBoletim, Long idDisciplina, Long idBimestre);

}
