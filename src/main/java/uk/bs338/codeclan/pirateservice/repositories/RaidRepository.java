package uk.bs338.codeclan.pirateservice.repositories;


import uk.bs338.codeclan.pirateservice.models.Raid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaidRepository extends JpaRepository<Raid, Long> {
}
