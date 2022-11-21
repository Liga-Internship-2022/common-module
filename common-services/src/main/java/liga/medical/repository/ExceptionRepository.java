package liga.medical.repository;

import liga.medical.entity.ExceptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExceptionRepository extends JpaRepository<ExceptionEntity, UUID> {
}
