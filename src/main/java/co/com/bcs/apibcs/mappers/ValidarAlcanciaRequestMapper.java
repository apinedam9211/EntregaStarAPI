package co.com.bcs.apibcs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.apibcs.services.dto.*;
import co.com.bcs.backend.services.client.sam.ValidarAlcanciaRequestType;

@Mapper(componentModel = "spring", uses = {CabecerasMapper.class , IdentificacionTypeMapper.class})
public interface ValidarAlcanciaRequestMapper {

	@Mapping(source = "cabecera", target = "cabeceraEntrada")
	@Mapping(source = "request.identificacion", target = "identificacion")
	@Mapping(source = "request.correo", target = "correoElectronico")
	@Mapping(source = "request.celular", target = "celular")
	@Mapping(target = "claveAlcancia", constant = "")
	public ValidarAlcanciaRequestType toValidarAlcanciaRequest(ValidarDepositoRequest request);
}