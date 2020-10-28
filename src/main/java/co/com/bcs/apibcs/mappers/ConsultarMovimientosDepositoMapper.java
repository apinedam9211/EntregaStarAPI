package co.com.bcs.apibcs.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoRequest;
import co.com.bcs.apibcs.services.dto.ConsultarMovimientosDepositoResponse;
import co.com.bcs.apibcs.services.dto.ConsultarSaldoDepositoRequest;
import co.com.bcs.apibcs.services.dto.MovimientosDeposito;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaRequestType;
import co.com.bcs.backend.services.client.sam.ConsultarMovimientosAlcanciaResponseType;
import co.com.bcs.backend.services.client.sam.ListaMovimientosType;

@Mapper(componentModel = "spring", uses = { CabecerasMapper.class, IdentificacionTypeMapper.class })
public interface ConsultarMovimientosDepositoMapper {

    @Mapping(source = "cabecera", target = "cabeceraEntrada")
    @Mapping(source = "request.cliente", target = "identificacion")
    @Mapping(source = "request.celular", target = "celular")
    @Mapping(target = "paginacion.registroInicial", constant = "1")
    @Mapping(target = "paginacion.registrosPorPag", constant = "0")
    @Mapping(target = "paginacion.rangoFecha.fechaDesde", constant = "")
    @Mapping(target = "paginacion.rangoFecha.fechaHasta", constant = "")
    ConsultarMovimientosAlcanciaRequestType consultarMovimientos(ConsultarSaldoDepositoRequest request);

    @Mapping(source = "cabecera", target = "cabeceraEntrada")
    @Mapping(source = "request.cliente", target = "identificacion")
    @Mapping(source = "request.celular", target = "celular")
    @Mapping(target = "paginacion.registroInicial", source = "request.pagina")
    @Mapping(target = "paginacion.registrosPorPag", constant = "0")
    @Mapping(target = "paginacion.rangoFecha.fechaDesde", constant = "")
    @Mapping(target = "paginacion.rangoFecha.fechaHasta", constant = "")
    ConsultarMovimientosAlcanciaRequestType consultarMovimientos(ConsultarMovimientosDepositoRequest request);

    @Mapping(target = "movimientos", source = "listaMovimientos", defaultExpression = "java(new ArrayList<>())")
    ConsultarMovimientosDepositoResponse consultarMovimientosResponse(
            ConsultarMovimientosAlcanciaResponseType response);

    default List<MovimientosDeposito> map(ListaMovimientosType lista) {

        return Optional.ofNullable(lista).map(ListaMovimientosType::getMovimientos).orElse(new ArrayList<>()).stream()
                .map(x -> new MovimientosDeposito(x.getFecha(), x.getDescripcion(), x.getValor()))
                .collect(Collectors.toList());
    }

}
