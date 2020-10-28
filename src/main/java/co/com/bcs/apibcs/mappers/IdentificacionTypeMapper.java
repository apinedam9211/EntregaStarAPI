package co.com.bcs.apibcs.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.bcs.apibcs.services.dto.Identificacion;
import co.com.bcs.backend.services.client.sam.IdentificacionType;

@Mapper(componentModel = "spring")
public interface IdentificacionTypeMapper {

	@Mapping(source = "tipoId", target = "tpIdentidicacion")
	@Mapping(source = "id", target = "numIdentificacion")
	public IdentificacionType toIdentificacionType(Identificacion identificacion);
	
	@Mapping(source = "tipoId", target = "tpIdentidicacion")
	@Mapping(source = "id", target = "numIdentificacion")
	public co.com.bcs.backend.services.client.samotc.IdentificacionType toIdentificacionTypeOTC(Identificacion identificacion);

}
