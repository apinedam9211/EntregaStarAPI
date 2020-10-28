package co.com.bcs.apibcs.mappers;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.DefaultOAuth2AuthenticatedPrincipal;

import co.com.bcs.apibcs.dto.v1.entities.Cabecera;
import co.com.bcs.apibcs.entity.ApiBCSAliado;
import co.com.bcs.apibcs.exception.Error500Exception;
import co.com.bcs.apibcs.repository.ApiBCSAliadoRepository;
import co.com.bcs.apibcs.services.dto.Cabeceras;

@Mapper(componentModel = "spring")
public abstract class CabecerasFrontMapper {

    @Autowired
    protected ApiBCSAliadoRepository repository;

    @Mapping(target = "codigoCorresponsal", expression = "java(getCodigoCorresponsal())")
    @Mapping(target = "codigoTerminal", expression = "java(getCodigoTerminal())")
    public abstract Cabeceras toCabeceras(Cabecera cabecera);

    String getCodigoCorresponsal() {

        Optional<ApiBCSAliado> aliado = repository.findByNombreAliado(getPrincipalName());

        if (aliado.isPresent()) {
            ApiBCSAliado aliadoGet = aliado.get();
            return aliadoGet.getCodigoCorresponsal();
        }

        throw new Error500Exception("Aliado no Configurado en Base de Datos", "0001",
                "En este momento no podemos atenderlo");

    }

    String getCodigoTerminal() {

        Optional<ApiBCSAliado> aliado = repository.findByNombreAliado(getPrincipalName());

        if (aliado.isPresent()) {
            ApiBCSAliado aliadoGet = aliado.get();
            return aliadoGet.getCodigoTerminal();
        }

        throw new Error500Exception("Aliado no Configurado en Base de Datos", "0001",
                "En este momento no podemos atenderlo");

    }

    public String getPrincipalName() {
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