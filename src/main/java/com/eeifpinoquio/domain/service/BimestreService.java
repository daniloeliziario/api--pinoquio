package com.eeifpinoquio.domain.service;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eeifpinoquio.domain.exception.RecursoNaoEncontradoException;
import com.eeifpinoquio.domain.model.Bimestre;
import com.eeifpinoquio.domain.repository.BoletimRepository;

@Service
public class BimestreService {
	
	@Autowired
	private BoletimRepository boletimRepository;
	
	private static final String RECURSO_BIMESTRE = "Bimestre";
	
	@Transactional
	public Bimestre alterar(Long idBoletim, Long idDisciplina, Long idBimestre, Bimestre bimestreAtualizado) {	
		
		Bimestre bimestreAtual = buscarOuFalhar(idBoletim, idDisciplina, idBimestre);		
		
		bimestreAtual.setPrimeiraNota(bimestreAtualizado.getPrimeiraNota());
		bimestreAtual.setSegundaNota(bimestreAtualizado.getSegundaNota());
		bimestreAtual.setTerceiraNota(bimestreAtualizado.getTerceiraNota());
		bimestreAtual.setRecuperacao(bimestreAtualizado.getRecuperacao());
		bimestreAtual.setFalta(bimestreAtualizado.getFalta());		
		bimestreAtual.setMedia(calcularMedia(bimestreAtual.getPrimeiraNota(), bimestreAtual.getSegundaNota(), bimestreAtual.getTerceiraNota(), bimestreAtual.getRecuperacao()));
		
		return boletimRepository.save(bimestreAtual);
	}
	
	public Bimestre buscarOuFalhar(Long idBoletim, Long idDisciplina, Long idBimestre) {
		return boletimRepository.findBimestreById(idBoletim, idDisciplina, idBimestre)
				.orElseThrow(() -> new RecursoNaoEncontradoException(RECURSO_BIMESTRE, idBimestre));
	}
	
	private Double calcularMedia(Double primeiraNota, Double segundaNota, Double terceiraNota, Double recuperacao) {
		
		Double media = null;
		
		if(allNotNull(primeiraNota, segundaNota, terceiraNota)) {
			
			double menor = min(primeiraNota, segundaNota, terceiraNota);
			
			if(isNotEmpty(recuperacao) && recuperacao > menor) {				
				
				if(primeiraNota.equals(menor)) {
					return (recuperacao + segundaNota + terceiraNota) / 3;
				} else if(segundaNota.equals(menor)) {
					return (primeiraNota + recuperacao + terceiraNota) / 3;
				}
				return (primeiraNota + segundaNota + recuperacao) / 3;
			}
			return (primeiraNota + segundaNota + terceiraNota) / 3;
		}
		return media;
	}

}
