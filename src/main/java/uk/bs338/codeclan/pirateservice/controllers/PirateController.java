package uk.bs338.codeclan.pirateservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.pirateservice.models.Pirate;
import uk.bs338.codeclan.pirateservice.repositories.PirateRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pirates")
public class PirateController {
    @Autowired
    PirateRepository pirateRepository;

    //    @GetMapping(value = "/pirates")
    @GetMapping
    public ResponseEntity<List<Pirate>> getAllPirates() {
        System.out.printf("Route: getAllPirates!%n");
        return new ResponseEntity<>(pirateRepository.findAll(), HttpStatus.OK);
    }

    /* What is the difference between null and Optional.of(null)?
    Optional is defined as not containing null!
     */

    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Pirate>> getPirate(@PathVariable Long id) {
        System.out.printf("Route: getOnePirate id=%s%n", id);
        Optional<Pirate> pirate = pirateRepository.findById(id);
        System.out.printf("  pirate=%s id=%s%n", pirate, pirate.map(Pirate::getId));
        System.out.printf(" null=%s Optional[null]=%s%n", null, Optional.ofNullable(null));
        return new ResponseEntity<>(pirate, pirate.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Pirate> createPirate(@RequestBody Pirate pirate) {
        System.out.printf("postPirate: %s%n", pirate);
        System.out.printf("  %s %s, aged %s, ship %s %s%n",
                pirate.getFirstName(), pirate.getLastName(),
                pirate.getAge(),
                pirate.getShip().getId(), pirate.getShip().getName());
        pirateRepository.save(pirate);
//        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(pirate, HttpStatus.CREATED);
    }
}