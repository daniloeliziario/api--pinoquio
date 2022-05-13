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
	
	@Query("select bdp, bds, bdt, bdq from Boletim b join b.disciplinas bd join bd.primeiroBimestre bdp join bd.segundoBimestre bds join bd.terceiroBimestre bdt join bd.quartoBimestre bdq "
			+ "where b.id = :idBoletim and bd.id = :idDisciplina and (bdp.id = :idBimestre or bds.id = :idBimestre or bdt.id = :idBimestre or bdq.id = :idBimestre)")
	Optional<Bimestre> findBimestreById(Long idBoletim, Long idDisciplina, Long idBimestre);

}
