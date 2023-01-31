package uk.bs338.codeclan.pirateservice.controllers;


import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.pirateservice.models.Pirate;
import uk.bs338.codeclan.pirateservice.models.Raid;
import uk.bs338.codeclan.pirateservice.repositories.RaidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
public class RaidController {

    @Autowired
    RaidRepository raidRepository;

    @GetMapping(value = "/raids")
    public ResponseEntity<List<Raid>> getAllRaids(){
        return new ResponseEntity<>(raidRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/raids/{id}")
    public ResponseEntity<Optional<Raid>> getRaid(@PathVariable Long id){
        Optional<Raid> raid = raidRepository.findById(id);
        return new ResponseEntity<>(raid, raid.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/raids")
    public ResponseEntity<Raid> createRaid(@RequestBody Raid raid) {
        System.out.printf("createRaid: %s (location=%s loot=%s)%n", raid, raid.getLocation(), raid.getLoot());
        raidRepository.save(raid);
        return new ResponseEntity<>(raid, HttpStatus.CREATED);
    }
}
