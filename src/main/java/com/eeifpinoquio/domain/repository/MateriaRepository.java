package com.eeifpinoquio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{
	
	Optional<Materia> findByDescricao(String descricao);

}
