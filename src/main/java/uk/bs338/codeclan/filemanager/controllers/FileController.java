package uk.bs338.codeclan.filemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.repositories.FileRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/files")
public class FileController {
//    @Autowired
//    FileRepository fileRepository;
//
//    //    @GetMapping(value = "/pirates")
//    @GetMapping
//    public ResponseEntity<List<File>> getAllPirates() {
//        System.out.printf("Route: getAllPirates!%n");
//        return new ResponseEntity<>(fileRepository.findAll(), HttpStatus.OK);
//    }
//
//    /* What is the difference between null and Optional.of(null)?
//    Optional is defined as not containing null!
//     */
//
//    @GetMapping(value = "{id}")
//    public ResponseEntity<Optional<File>> getPirate(@PathVariable Long id) {
//        System.out.printf("Route: getOnePirate id=%s%n", id);
//        Optional<File> pirate = fileRepository.findById(id);
//        System.out.printf("  pirate=%s id=%s%n", pirate, pirate.map(File::getId));
//        System.out.printf(" null=%s Optional[null]=%s%n", null, Optional.ofNullable(null));
//        return new ResponseEntity<>(pirate, pirate.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping
//    public ResponseEntity<File> createPirate(@RequestBody File file) {
//        System.out.printf("postPirate: %s%n", file);
//        System.out.printf("  %s %s, aged %s, ship %s %s%n",
//                file.getFirstName(), file.getLastName(),
//                file.getAge(),
//                file.getShip().getId(), file.getShip().getName());
//        fileRepository.save(file);
////        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        return new ResponseEntity<>(file, HttpStatus.CREATED);
//    }
}