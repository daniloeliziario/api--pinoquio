package com.eeifpinoquio.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Ano;

@Repository
public interface AnoRepository extends JpaRepository<Ano, Long>{
	
	Optional<Ano> findByTitulo(String titulo);

}
