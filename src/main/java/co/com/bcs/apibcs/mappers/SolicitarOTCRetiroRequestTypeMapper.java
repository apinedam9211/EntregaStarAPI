package co.com.bcs.apibcs.mappers;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import co.com.bcs.apibcs.services.dto.OTCRetiroRequest;
import co.com.bcs.backend.services.client.samotc.SolicitarOTCRetiroRequestType;

@Mapper(componentModel = "spring", uses = { CabecerasMapper.class, IdentificacionTypeMapper.class })
public interface SolicitarOTCRetiroRequestTypeMapper {

	@Mapping(source = "request.identificacionCliente", target = "identificacion")
	@Mapping(source = "cabecera", target = "cabeceraEntrada")
	@Mapping(target = "origen", constant = "API BANCO")
	@Mapping(target = "maquina", constant = "")
	@Mapping(target = "codigoTx", constant = "01")
	@Mapping(target = "sesion", constant = "")	
	@Mapping(target = "datosAdicionales", constant = "")
	@Mapping(source = "request.correo", target = "correoElectronico")
	@Mapping(source = "request.celular", target = "datosRetiro.celular")
	@Mapping(source = "request.valorRetiro", target = "datosRetiro.valorRetiro" , qualifiedByName = "toDatosRetiro")
	@Mapping(target = "datosRetiro.medioRetiro", constant = "")	
	public SolicitarOTCRetiroRequestType toRegistroClienteRequestType(OTCRetiroRequest request);

	@Named("toDatosRetiro")
	default BigDecimal toDatosRetiro(String datos){
		
		if (datos==null){
			return BigDecimal.ZERO;
		}

        return new BigDecimal(datos + ".0");

	}

	

}
