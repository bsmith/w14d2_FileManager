package uk.bs338.codeclan.filemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.repositories.FileRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/files")
public class FileController {
    @Autowired
    FileRepository fileRepository;

    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        System.out.printf("Route: getAllFiles!%n");
        return new ResponseEntity<>(fileRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<File>> getOneFile(@PathVariable Long id) {
        System.out.printf("Route: getOneFile id=%s%n", id);
        Optional<File> file = fileRepository.findById(id);
        System.out.printf("  file=%s id=%s%n", file, file.map(File::getId));
        return new ResponseEntity<>(file, file.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }


    @PostMapping
    public ResponseEntity<File> createFile(@RequestBody File file) {
        System.out.printf("createFile: %s (%s folder=%s@%s)%n", file, file.formatFilename(),
                file.getFolder().getTitle(), file.getFolder().getId());
        fileRepository.save(file);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }
}