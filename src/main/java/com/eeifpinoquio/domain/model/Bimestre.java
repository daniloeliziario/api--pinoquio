package com.eeifpinoquio.domain.model;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.math.NumberUtils.min;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
public class Bimestre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double primeiraNota;
	
	private Double segundaNota;
	
	private Double terceiraNota;
	
	private Double recuperacao;
	
	private Double media;
	
	private Integer falta;
	
	@CreationTimestamp
	private OffsetDateTime dataCadastro;
	
	@UpdateTimestamp
	private OffsetDateTime dataAtualizacao;
	
	
	public Double calcularMedia(Double primeiraNota, Double segundaNota, Double terceiraNota, Double recuperacao) {
		
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
