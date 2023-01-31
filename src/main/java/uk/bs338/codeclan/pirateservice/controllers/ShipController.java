package uk.bs338.codeclan.pirateservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.pirateservice.models.Pirate;
import uk.bs338.codeclan.pirateservice.models.Raid;
import uk.bs338.codeclan.pirateservice.models.Ship;
import uk.bs338.codeclan.pirateservice.repositories.RaidRepository;
import uk.bs338.codeclan.pirateservice.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ShipController {

    @Autowired
    ShipRepository shipRepository;

    @GetMapping(value = "/ships")
    public ResponseEntity<List<Ship>> getAllShips(){
        return new ResponseEntity<>(shipRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    public ResponseEntity<Optional<Ship>> getShip(@PathVariable Long id){
        Optional<Ship> ship = shipRepository.findById(id);
        return new ResponseEntity<>(ship, ship.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/ships/{id}/pirates")
    public ResponseEntity<List<Pirate>> getPiratesOnShip(@PathVariable Long id) {
        Optional<Ship> ship = shipRepository.findById(id);
        return new ResponseEntity<>(ship.map(Ship::getPirates).orElseGet(() -> new ArrayList<>()),
                ship.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/ships")
    public ResponseEntity<Ship> createShip(@RequestBody Ship ship) {
        System.out.printf("createShip: %s (name=%s)%n", ship, ship.getName());
        shipRepository.save(ship);
        return new ResponseEntity<>(ship, HttpStatus.CREATED);
    }
}
