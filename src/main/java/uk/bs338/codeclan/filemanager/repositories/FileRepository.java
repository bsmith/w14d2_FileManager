package uk.bs338.codeclan.filemanager.repositories;

import uk.bs338.codeclan.filemanager.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
