package com.eeifpinoquio.api.controller.converter.boletim;

import java.util.List;

import com.eeifpinoquio.api.controller.converter.serie.SerieRequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoletimRequest {

	private List<SerieRequest> series;
}
