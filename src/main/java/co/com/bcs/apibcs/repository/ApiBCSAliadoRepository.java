package co.com.bcs.apibcs.repository;

import java.util.Optional;


import co.com.bcs.apibcs.entity.ApiBCSAliado;


public interface ApiBCSAliadoRepository  {

	Optional<ApiBCSAliado> findByNombreAliado(String nombreAliado);
}
