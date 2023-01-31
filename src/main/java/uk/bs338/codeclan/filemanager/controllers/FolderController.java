package uk.bs338.codeclan.filemanager.controllers;


import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.models.Person;
import uk.bs338.codeclan.filemanager.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/folders")
public class FolderController {
    @Autowired
    FolderRepository folderRepository;

    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders() {
        System.out.printf("Route: getAllFolders!%n");
        return new ResponseEntity<>(folderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Folder>> getOneFolder(@PathVariable Long id) {
        System.out.printf("Route: getOneFolder id=%s%n", id);
        Optional<Folder> folder = folderRepository.findById(id);
        System.out.printf("  folder=%s id=%s%n", folder, folder.map(Folder::getId));
        return new ResponseEntity<>(folder, folder.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        System.out.printf("createFolder: %s (title=%s person=%s@%s)%n", folder, folder.getTitle(),
                folder.getPerson().getName(), folder.getPerson().getId());
        folderRepository.save(folder);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }
}
