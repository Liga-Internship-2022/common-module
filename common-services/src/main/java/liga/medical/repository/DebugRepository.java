package liga.medical.repository;

import liga.medical.entity.DebugEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DebugRepository extends JpaRepository<DebugEntity, UUID> {
}
