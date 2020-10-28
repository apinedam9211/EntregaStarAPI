package co.com.bcs.apibcs.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.apibcs.services.dto.Cabeceras;
import co.com.bcs.apibcs.services.dto.SolicitarDeposito;
import co.com.bcs.backend.services.client.encriptacion.EncriptarMensajeAESRequest;
import co.com.bcs.backend.services.client.sam.FinalizarRegistroClienteRequestType;
import co.com.bcs.backend.services.client.sam.ListaArchivosType;
import co.com.bcs.backend.services.client.sam.RegistroClienteRequestType;

@Mapper(componentModel = "spring", uses = { CabecerasMapper.class, IdentificacionTypeMapper.class })
public interface SolicitarDepositoElectronicoMapper {

	@Mapping(source = "request.cabecera", target = "cabeceraEntrada")
	@Mapping(source = "request.identificacionCliente", target = "idCliente")
	@Mapping(source = "request.celular", target = "celular")
	@Mapping(source = "clave", target = "clave")
	@Mapping(target = "lenddoAID", constant = "")
	@Mapping(target = "flagActualizaCorreo", constant = "")
	@Mapping(target = "idPromotor.tpIdentidicacion", constant = "")
	@Mapping(target = "idPromotor.numIdentificacion", constant = "")
	@Mapping(target = "listaArchivos", expression = "java(listaArchivosType())")
	public FinalizarRegistroClienteRequestType toFinalizarRegistroClienteRequestType(SolicitarDeposito request,
			String clave);

	@Mapping(source = "request.identificacionCliente", target = "identificacion")
	@Mapping(source = "cabecera", target = "cabeceraEntrada")
	@Mapping(source = "request.celular", target = "infoPersona.numeroCelular")
	@Mapping(source = "request.correoElectronico", target = "infoPersona.correoElectronico")
	@Mapping(source = "request.fechaExpedicionDocumento", target = "infoPersona.fchExpedicion")
	@Mapping(source = "request.fechaNacimiento", target = "infoPersona.fchNacimiento")
	@Mapping(source = "request.genero", target = "infoPersona.genero")
	@Mapping(target = "infoPersona.aniosOcupacion", constant = "0")
	@Mapping(target = "infoPersona.grupoSanguineo", constant = "")
	@Mapping(target = "infoPersona.mesesOcupacion", constant = "0")
	@Mapping(target = "infoPersona.nivelEducativo", constant = "")
	@Mapping(target = "infoPersona.ocupacion", constant = "")
	@Mapping(target = "infoPersona.personasACargo", constant = "0")
	@Mapping(target = "lenddoAID", constant = "")
	@Mapping(source = "request.primerApellido", target = "nombreCompleto.primerApellido")
	@Mapping(source = "request.segundoApellido", target = "nombreCompleto.segundoApellido")
	@Mapping(source = "request.primerNombre", target = "nombreCompleto.primerNombre")
	@Mapping(source = "request.segundoNombre", target = "nombreCompleto.segundoNombre")
	public RegistroClienteRequestType toRegistroClienteRequestType( SolicitarDeposito request);

	@Mapping(source = "cabecera", target = "cabeceraEntrada")
	@Mapping(source = "textoAEncriptar", target = "textoAEncriptar")
	public EncriptarMensajeAESRequest toEncriptarMensajeAESRequest(Cabeceras cabecera,
			String textoAEncriptar);

	default ListaArchivosType listaArchivosType() {
		return new ListaArchivosType();
	}
}
