package uk.bs338.codeclan.filemanager.controllers;


import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/folders")
public class FolderController {
//
//    @Autowired
//    FolderRepository folderRepository;
//
//    @GetMapping(value = "/raids")
//    public ResponseEntity<List<Folder>> getAllRaids(){
//        return new ResponseEntity<>(folderRepository.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/raids/{id}")
//    public ResponseEntity<Optional<Folder>> getRaid(@PathVariable Long id){
//        Optional<Folder> raid = folderRepository.findById(id);
//        return new ResponseEntity<>(raid, raid.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
//    }
//
//    @PostMapping(value = "/raids")
//    public ResponseEntity<Folder> createRaid(@RequestBody Folder folder) {
//        System.out.printf("createRaid: %s (location=%s loot=%s)%n", folder, folder.getLocation(), folder.getLoot());
//        folderRepository.save(folder);
//        return new ResponseEntity<>(folder, HttpStatus.CREATED);
//    }
}
