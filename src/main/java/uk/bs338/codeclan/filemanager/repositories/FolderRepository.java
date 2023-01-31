package uk.bs338.codeclan.filemanager.repositories;


import uk.bs338.codeclan.filemanager.models.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
