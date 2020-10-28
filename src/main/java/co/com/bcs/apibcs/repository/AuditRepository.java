package co.com.bcs.apibcs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import co.com.bcs.apibcs.entity.AuditAPI;

public interface AuditRepository extends JpaRepository<AuditAPI, UUID> {

}
