package co.com.bcs.apibcs.mappers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Value;

import co.com.bcs.apibcs.services.dto.CreacionMedioManejoRequest;
import co.com.bcs.apibcs.services.dto.CreditoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.DebitoDepositoElectronicoRequest;
import co.com.bcs.apibcs.services.dto.Identificacion;
import co.com.bcs.apibcs.tuxedo.objects.CreditoTuxedoRequest;
import co.com.bcs.apibcs.tuxedo.objects.RetiroTuxedoRequest;
import co.com.bcs.apibcs.tuxedo.objects.SolicitudMedioManejoRequest;
import lombok.AccessLevel;
import lombok.Getter;

@Mapper(componentModel = "spring")
public abstract class TuxedoMappers {

	@Value("${canal.credito}")
	public @Getter(value = AccessLevel.PROTECTED) String canalCredito;

	@Value("${subcanal.credito}")
	public @Getter(value = AccessLevel.PROTECTED) String subCanalCredito;

	@Value("${canal.debito}")
	public @Getter(value = AccessLevel.PROTECTED) String canalDebito;

	@Value("${subcanal.debito}")
	public @Getter(value = AccessLevel.PROTECTED) String subCanalDebito;

	@Mapping(target = "canal", expression = "java(getCanalDebito())")
	@Mapping(target = "subcanal", expression = "java(getSubCanalDebito())") // temporal, cambiar a 06
	@Mapping(source = "request.cliente", target = "numeroProducto", qualifiedByName = "toNumeroProducto")
	@Mapping(target = "tipoTransaccion", constant = "0200")
	@Mapping(target = "codigoProcesamiento", constant = "01A000")
	@Mapping(target = "valorTransaccion", source = "request.monto", qualifiedByName = "toMonto")
	@Mapping(target = "numeroOriginador", source = "request.referencia", qualifiedByName = "toNumeroOriginador")
	@Mapping(target = "hora", expression = "java(getHora())")
	@Mapping(target = "fechaPosteo", expression = "java(getFechaPosteo())")
	@Mapping(target = "codigoEntidadAsociada", constant = "1110000000180")
	@Mapping(target = "otroParametro", constant = "1110000000180")
	@Mapping(target = "datosRastreo", source = "request.cliente", qualifiedByName = "toDatosRastreo")
	@Mapping(target = "idTerminal", source = "cabecera.codigoTerminal")
	@Mapping(target = "pin", source = "request.pinBlock")
	@Mapping(target = "datosTerminal", constant = "0120180TES1+000")
	@Mapping(target = "cardIssuer", constant = "0132180TES10011P")
	@Mapping(target = "tokenQM", constant = "")
	@Mapping(target = "codigoInstitucion", constant = "10000002180")
	public abstract RetiroTuxedoRequest toRetiroTuxedoRequest(DebitoDepositoElectronicoRequest request);

	// @Mapping(target = "puntoServicio", constant = "0027875301") // Debe cambiarse
	// a base de datos, validar que el punto sea el mismo que en debito.
	// @Mapping(target = "codigoSitio", constant = "PRUEBAS BCS 9191
	// 1100100BOGOTACUNCO") // poner en base de datos // TODO.
	@Mapping(target = "canal", expression = "java(getCanalCredito())")
	@Mapping(target = "subcanal", expression = "java(getSubCanalCredito())") // temporal, cambiar a 06
	@Mapping(source = "request.cliente", target = "numeroProducto", qualifiedByName = "toNumeroProducto")
	@Mapping(target = "tipoTransaccion", constant = "0200")
	@Mapping(target = "codigoProcesamiento", constant = "090010")
	@Mapping(target = "valorTransaccion", source = "request.monto", qualifiedByName = "toMonto")
	@Mapping(target = "numeroOriginador", source = "request.referencia", qualifiedByName = "toNumeroOriginador")
	@Mapping(target = "hora", expression = "java(getHora())")
	@Mapping(target = "fechaPosteo", expression = "java(getFechaPosteo())")
	@Mapping(target = "codigoEntidadAsociada", constant = "1110000000180")
	@Mapping(target = "otroParametro", constant = "1110000000180")
	@Mapping(target = "datosRastreo", source = "request.cliente", qualifiedByName = "toDatosRastreo")
	@Mapping(target = "idTerminal", source = "cabecera.codigoTerminal")
	@Mapping(target = "datosTerminal", constant = "0120180TES1+000")
	@Mapping(target = "cardIssuer", constant = "0132180TES10011P")
	@Mapping(target = "codigoInstitucion", constant = "10000002180")
	public abstract CreditoTuxedoRequest toCreditoTuxedoRequest(CreditoDepositoElectronicoRequest request);

	@Named("toNumeroOriginador")
	String toNumeroOriginador(String referencia) {

		return StringUtils.leftPad(referencia, 12, "0").substring(6, 12);
	}

	@Named("toDatosRastreo")
	String toDatosRastreo(Identificacion cliente) {
		return "37" + toNumeroProducto(cliente) + "=18122260000021100";
	}

	@Named("toNumeroProducto")
	String toNumeroProducto(Identificacion cliente) {

		String client = StringUtils.leftPad(cliente.id, 10, "0");
		return StringUtils.rightPad("890082" + client, 19);

	}

	@Named("toMonto")
	String toMonto(String montoStr) {
		if (null == montoStr || montoStr.isEmpty()) {
			return "0";
		}
		return montoStr + "00";
	}

	String getHora() {

		LocalDateTime now = LocalDateTime.now();

		Integer hour = now.getHour();
		Integer minute = now.getMinute();
		Integer second = now.getSecond();
		return StringUtils.leftPad(hour.toString(), 2, "0") + StringUtils.leftPad(minute.toString(), 2, "0")
				+ StringUtils.leftPad(second.toString(), 2, "0");

	}

	String getFechaPosteo() {

		LocalDateTime now = LocalDateTime.now();

		Integer day = now.getDayOfMonth();
		Integer month = now.getMonthValue();

		return StringUtils.leftPad(month.toString(), 2, "0") + StringUtils.leftPad(day.toString(), 2, "0");

	}

	@Mapping(target = "cabecera", source = "cabecera")
	@Mapping(target = "tipoID", source = "cliente.tipoId")
	@Mapping(target = "id", source = "cliente.id")
	@Mapping(target = "numCuenta", source = "cliente.id")
	@Mapping(target = "tipoProceso", constant = "1")
	@Mapping(target = "numeroTarjetaAnterior", constant = "")
	@Mapping(target = "codigoRazonCambio", constant = "")
	@Mapping(target = "tipoEntrega", constant = "NAL")
	@Mapping(target = "canalSolicitud", constant = "API")
	@Mapping(target = "identificadorAliado", constant = "")
	@Mapping(target = "cobroTarjeta", constant = "false")
	@Mapping(target = "cobroServicio", constant = "false")
	@Mapping(target = "direccionEntrega", source = "direccion")
	@Mapping(target = "ciudadEntrega", source = "ciudad")
	@Mapping(target = "nombrePais", constant = "Colombia")
	@Mapping(target = "codigoPostal", constant = "")
	@Mapping(target = "correoElectronico", source = "correo")
	@Mapping(target = "cabecera.countryCode" , constant="CO")
	@Mapping(target = "cabecera.networkId", constant = "32")
	@Mapping(target = "cabecera.canal", constant = "13")
	@Mapping(target = "cabecera.subcanal", constant = "6")
	@Mapping(target = "cabecera.idTerminal", source = "cabecera.codigoTerminal")
	@Mapping(target = "cabecera.usuario", source = "cabecera.codigoCorresponsal")
	@Mapping(target = "cabecera.ipCliente", source = "cabecera.ipClienteCorresponsal")
	@Mapping(target = "cabecera.macCliente", source = "cabecera.macClienteCorresponsal")
	@Mapping(target = "cabecera.ipServer", source = "cabecera.ipServidorCorresponsal")
	@Mapping(target = "cabecera.macServer", source = "cabecera.macServidorCorresponsal")
	@Mapping(target = "cabecera.llaveSesion", constant = "")
	@Mapping(target = "cabecera.nombreServicio", constant = "APIBCO_SOLTARDE")
	public abstract SolicitudMedioManejoRequest toSolicitudMedioManejo(CreacionMedioManejoRequest creacionMedioManejo);

}