package co.com.bcs.apibcs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;

import co.com.bcs.apibcs.services.dto.Cabeceras;
import co.com.bcs.backend.services.client.sam.CabeceraEntrada;
import lombok.AccessLevel;
import lombok.Getter;

@Mapper(componentModel = "spring")
public abstract class CabecerasMapper {

	@Value("${canal.sam}")
	public @Getter(value = AccessLevel.PROTECTED) String canalSAM;

	@Value("${subcanal.sam}")
	public  @Getter(value = AccessLevel.PROTECTED) String subCanalSAM;

	@Value("${canal.encriptar}")
	public  @Getter(value = AccessLevel.PROTECTED) String canalEncriptar;

	@Value("${subcanal.encriptar}")
	public @Getter(value = AccessLevel.PROTECTED) String subCanalEncriptar;

	@Value("${canal.otc}")
	public @Getter(value = AccessLevel.PROTECTED) String canalOTC;

	@Value("${subcanal.otc}")
	public @Getter(value = AccessLevel.PROTECTED)String subCanalOTC;

	@Mapping(source = "cabecera.codigoCorresponsal", target = "invocador.codigoCliente")
	@Mapping(source = "cabecera.codigoTerminal", target = "invocador.codigoTerminal")
	@Mapping(source = "cabecera.ipClienteCorresponsal", target = "invocador.direccionIpCliente")
	@Mapping(source = "cabecera.macClienteCorresponsal", target = "invocador.direccionMacCliente")
	@Mapping(source = "cabecera.ipServidorCorresponsal", target = "invocador.direccionIpServidor")
	@Mapping(source = "cabecera.macServidorCorresponsal", target = "invocador.direccionMacServidor")
	@Mapping(source = "cabecera.paisOrigen", target = "invocador.pais")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.numeroReferencia")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.identificadorTx")
	@Mapping(expression = "java(getPrincipalName())", target = "seguridad.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuarioModificacion")
	@Mapping(target = "invocador.accion", constant = "")
	@Mapping(target = "invocador.canalOrigen", expression =  "java(getCanalSAM())")
	@Mapping(target = "invocador.codigoATM", constant = "")
	@Mapping(target = "invocador.codigoOficina", constant = "0670")
	@Mapping(target = "invocador.componente", constant = "")
	@Mapping(target = "invocador.destino", constant = "")
	@Mapping(target = "invocador.fechaProceso", constant = "")
	@Mapping(target = "invocador.llaveSesion", constant = "")
	@Mapping(target = "invocador.numeroSolicitud", constant = "")
	@Mapping(target = "invocador.origen", constant = "32")
	@Mapping(target = "invocador.procesoBpmId", constant = "")
	@Mapping(target = "invocador.procesoId", constant = "391")
	@Mapping(target = "invocador.red", constant = "32")
	@Mapping(target = "invocador.subcanal", expression =  "java(getSubCanalSAM())")
	public abstract CabeceraEntrada toCabeceraEntrada(Cabeceras cabecera);

	@Mapping(source = "cabecera.codigoCorresponsal", target = "invocador.codigoCliente")
	@Mapping(source = "cabecera.codigoTerminal", target = "invocador.codigoTerminal")
	@Mapping(source = "cabecera.ipClienteCorresponsal", target = "invocador.direccionIpCliente")
	@Mapping(source = "cabecera.macClienteCorresponsal", target = "invocador.direccionMacCliente")
	@Mapping(source = "cabecera.ipServidorCorresponsal", target = "invocador.direccionIpServidor")
	@Mapping(source = "cabecera.macServidorCorresponsal", target = "invocador.direccionMacServidor")
	@Mapping(source = "cabecera.paisOrigen", target = "invocador.pais")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.numeroReferencia")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.identificadorTx")
	@Mapping(expression = "java(getPrincipalName())", target = "seguridad.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuarioModificacion")
	@Mapping(target = "invocador.accion", constant = "")
	@Mapping(target = "invocador.canalOrigen", expression =  "java(getCanalEncriptar())")
	@Mapping(target = "invocador.codigoATM", constant = "")
	@Mapping(target = "invocador.codigoOficina", constant = "0670")
	@Mapping(target = "invocador.componente", constant = "")
	@Mapping(target = "invocador.destino", constant = "")
	@Mapping(target = "invocador.fechaProceso", constant = "")
	@Mapping(target = "invocador.llaveSesion", constant = "")
	@Mapping(target = "invocador.numeroSolicitud", constant = "")
	@Mapping(target = "invocador.origen", constant = "32")
	@Mapping(target = "invocador.procesoBpmId", constant = "")
	@Mapping(target = "invocador.procesoId", constant = "391")
	@Mapping(target = "invocador.red", constant = "32")
	@Mapping(target = "invocador.subcanal", expression =  "java(getSubCanalEncriptar())")
	public abstract co.com.bcs.backend.services.client.encriptacion.CabeceraEntrada toCabeceraEncriptacion(
			Cabeceras cabecera);

	@Mapping(source = "cabecera.codigoCorresponsal", target = "invocador.codigoCliente")
	@Mapping(source = "cabecera.codigoTerminal", target = "invocador.codigoTerminal")
	@Mapping(source = "cabecera.ipClienteCorresponsal", target = "invocador.direccionIpCliente")
	@Mapping(source = "cabecera.macClienteCorresponsal", target = "invocador.direccionMacCliente")
	@Mapping(source = "cabecera.ipServidorCorresponsal", target = "invocador.direccionIpServidor")
	@Mapping(source = "cabecera.macServidorCorresponsal", target = "invocador.direccionMacServidor")
	@Mapping(source = "cabecera.paisOrigen", target = "invocador.pais")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.numeroReferencia")
	@Mapping(source = "cabecera.numeroReferencia", target = "invocador.identificadorTx")
	@Mapping(expression = "java(getPrincipalName())", target = "seguridad.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuario")
	@Mapping(expression = "java(getPrincipalName())", target = "invocador.usuarioModificacion")
	@Mapping(target = "invocador.accion", constant = "")
	@Mapping(target = "invocador.canalOrigen", expression =  "java(getCanalOTC())")
	@Mapping(target = "invocador.codigoATM", constant = "")
	@Mapping(target = "invocador.codigoOficina", constant = "320670")
	@Mapping(target = "invocador.componente", constant = "")
	@Mapping(target = "invocador.destino", constant = "")
	@Mapping(target = "invocador.fechaProceso", constant = "")
	@Mapping(target = "invocador.llaveSesion", constant = "")
	@Mapping(target = "invocador.numeroSolicitud", constant = "")
	@Mapping(target = "invocador.origen", constant = "32")
	@Mapping(target = "invocador.procesoBpmId", constant = "")
	@Mapping(target = "invocador.procesoId", constant = "391")
	@Mapping(target = "invocador.red", constant = "32")
	@Mapping(target = "invocador.subcanal", expression =  "java(getSubCanalOTC())")
	public abstract co.com.bcs.backend.services.client.samotc.CabeceraEntrada toCabeceraEntradaOTC(Cabeceras cabecera);

	String getPrincipalName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof DefaultOAuth2AuthenticatedPrincipal) {
			return ((DefaultOAuth2AuthenticatedPrincipal) principal).getName();
		}
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		} else {
			return principal.toString();
		}
	}

}