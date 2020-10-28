package co.com.bcs.apibcs.mappers;

import javax.validation.Valid;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import co.com.bcs.apibcs.dto.v1.entities.Credito;
import co.com.bcs.apibcs.dto.v1.entities.Debito;
import co.com.bcs.apibcs.dto.v1.entities.DepositoID;
import co.com.bcs.apibcs.dto.v1.entities.DepositoSearchDTO;
import co.com.bcs.apibcs.dto.v1.entities.MedioManejo;
import co.com.bcs.apibcs.dto.v1.entities.Otc;
import co.com.bcs.apibcs.dto.v1.entities.SolicitudDeposito;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarSaldoDepositoRequest;
import co.com.bcs.apibcs.services.dto.CreacionMedioManejoRequest;
import co.com.bcs.apibcs.services.dto.CreditoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.DebitoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.OTCRetiroRequest;
import co.com.bcs.apibcs.services.dto.SolicitarDeposito;
import co.com.bcs.apibcs.services.dto.ValidarDepositoRequest;

@Mapper(componentModel = "spring", uses = { CabecerasFrontMapper.class, IdentificacionTypeMapper.class })
public interface ControllerMapper {

  @Mapping(source = "otc.celular", target = "celular")
  @Mapping(target = "cabecera", source = "cabecera")
  @Mapping(source = "depositoID.numeroDocumento", target = "identificacionCliente.id")
  @Mapping(source = "depositoID.tipoDocumento", target = "identificacionCliente.tipoId")
  @Mapping(source = "otc.valorRetiro", target = "valorRetiro")
  @Mapping(source = "otc.correo", target = "correo")
  OTCRetiroRequest toOTCRetiroRequest(Cabecera cabecera, Otc otc, DepositoID depositoID);

  @Mapping(source = "celular", target = "celular")
  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "depositoID.numeroDocumento", target = "cliente.id")
  @Mapping(source = "depositoID.tipoDocumento", target = "cliente.tipoId")
  @Mapping(source = "pagina", target = "pagina")
  ConsultarMovimientosDepositoRequest toConsultarMovimientosDepositoRequest(Integer pagina, Cabecera cabecera,
      DepositoID depositoID, String celular);

  @Mapping(source = "celular", target = "celular")
  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "depositoID.numeroDocumento", target = "cliente.id")
  @Mapping(source = "depositoID.tipoDocumento", target = "cliente.tipoId")
  ConsultarSaldoDepositoRequest toConsultarSaldoDepositoRequest(Cabecera cabecera, DepositoID depositoID,
      String celular);

  @Mapping(source = "request.monto", target = "monto")
  @Mapping(source = "request.referencia", target = "referencia")
  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "depositoID.numeroDocumento", target = "cliente.id")
  @Mapping(source = "depositoID.tipoDocumento", target = "cliente.tipoId")
  CreditoDepositoElectronicoRequest toCreditoDepositoElectronicoRequest(Credito request, Cabecera cabecera,
      DepositoID depositoID);

  @Mapping(source = "request.monto", target = "monto")
  @Mapping(source = "request.referencia", target = "referencia")
  @Mapping(source = "request.pinBlock", target = "pinBlock")
  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "depositoID.numeroDocumento", target = "cliente.id")
  @Mapping(source = "depositoID.tipoDocumento", target = "cliente.tipoId")
  DebitoDepositoElectronicoRequest toDebitoDepositoElectronicoRequest(Debito request, Cabecera cabecera,
      DepositoID depositoID);

  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "request.numeroDocumento", target = "identificacionCliente.id")
  @Mapping(source = "request.tipoDocumento", target = "identificacionCliente.tipoId")
  @Mapping(source = "request.primerNombre", target = "primerNombre")
  @Mapping(source = "request.segundoNombre", target = "segundoNombre")
  @Mapping(source = "request.celular", target = "celular")
  @Mapping(source = "request.primerApellido", target = "primerApellido")
  @Mapping(source = "request.segundoApellido", target = "segundoApellido")
  @Mapping(source = "request.correoElectronico", target = "correoElectronico")
  @Mapping(source = "request.fechaNacimiento", target = "fechaNacimiento")
  @Mapping(source = "request.genero", target = "genero")
  @Mapping(source = "request.fechaExpedicionDocumento", target = "fechaExpedicionDocumento")
  @Mapping(source = "request.clave", target = "clave")
  SolicitarDeposito toSolicitarDeposito(SolicitudDeposito request, Cabecera cabecera);

  @Mapping(source = "depositoSearchDTO.celular", target = "celular")
  @Mapping(source = "depositoSearchDTO.correo", target = "correo")
  @Mapping(source = "cabecera", target = "cabecera")
  @Mapping(source = "depositoSearchDTO.numeroDocumento", target = "identificacion.id")
  @Mapping(source = "depositoSearchDTO.tipoDocumento", target = "identificacion.tipoId")
  ValidarDepositoRequest toValidarDepositoRequest(DepositoSearchDTO depositoSearchDTO, Cabecera cabecera);

  @Mapping(target = "cabecera", source = "cabecera")
  @Mapping(source = "id.numeroDocumento", target = "cliente.id")
  @Mapping(source = "id.tipoDocumento", target = "cliente.tipoId")
  @Mapping(source = "request.correo", target = "correo")
  @Mapping(source = "request.direccion", target = "direccion")
  @Mapping(source = "request.ciudad", target = "ciudad")
  CreacionMedioManejoRequest toCreacionMedioManejoRequest(@Valid MedioManejo request, @Valid Cabecera cabecera,
      @Valid DepositoID id);

}