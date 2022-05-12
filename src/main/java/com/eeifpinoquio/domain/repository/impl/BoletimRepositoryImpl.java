package com.eeifpinoquio.domain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.model.Bimestre;
import com.eeifpinoquio.domain.repository.BoletimRepositoryQuery;

public class BoletimRepositoryImpl implements BoletimRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	@Override
	public Bimestre save(Bimestre bimestre) {
		return entityManager.merge(bimestre);
	}

}
