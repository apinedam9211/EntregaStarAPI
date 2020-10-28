package co.com.bcs.apibcs.repository;

import java.util.Optional;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import co.com.bcs.apibcs.entity.ApiBCSAliado;

@Repository
@Profile("tomcat")
public class APIBCSAliadoRepositoryMemory implements ApiBCSAliadoRepository {

    @Override
    public Optional<ApiBCSAliado> findByNombreAliado(String nombreAliado) {
        ApiBCSAliado aliado = new ApiBCSAliado();
        aliado.setCodigoCorresponsal("codigoCorresponsal");
        aliado.setCodigoTerminal("codigoTerminal");
        return Optional.of(aliado);
    }
    


}