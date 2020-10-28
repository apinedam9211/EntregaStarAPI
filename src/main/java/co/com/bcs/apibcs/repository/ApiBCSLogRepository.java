package co.com.bcs.apibcs.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.bcs.apibcs.entity.ApiBCSLog;

@Repository
public interface ApiBCSLogRepository extends JpaRepository<ApiBCSLog, UUID> {

}
