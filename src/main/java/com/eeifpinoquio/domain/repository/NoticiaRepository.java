package com.eeifpinoquio.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eeifpinoquio.domain.model.Noticia;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

}
