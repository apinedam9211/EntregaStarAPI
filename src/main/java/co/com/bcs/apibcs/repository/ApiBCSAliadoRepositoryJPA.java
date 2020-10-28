package co.com.bcs.apibcs.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.bcs.apibcs.entity.ApiBCSAliado;

@Repository
@Profile("weblogic")
public interface ApiBCSAliadoRepositoryJPA extends ApiBCSAliadoRepository,JpaRepository<ApiBCSAliado, String> {

	Optional<ApiBCSAliado> findByNombreAliado(String nombreAliado);
}
