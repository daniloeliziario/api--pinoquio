package com.eeifpinoquio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Materia;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Long>{

}