package com.eeifpinoquio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long>{

}
