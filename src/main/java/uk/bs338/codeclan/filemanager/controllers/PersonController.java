package uk.bs338.codeclan.filemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Person;
import uk.bs338.codeclan.filemanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping(value = "/ships")
    public ResponseEntity<List<Person>> getAllShips(){
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/ships/{id}")
    public ResponseEntity<Optional<Person>> getShip(@PathVariable Long id){
        Optional<Person> ship = personRepository.findById(id);
        return new ResponseEntity<>(ship, ship.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/ships/{id}/pirates")
    public ResponseEntity<List<File>> getPiratesOnShip(@PathVariable Long id) {
        Optional<Person> ship = personRepository.findById(id);
        return new ResponseEntity<>(ship.map(Person::getPirates).orElseGet(() -> new ArrayList<>()),
                ship.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/ships")
    public ResponseEntity<Person> createShip(@RequestBody Person person) {
        System.out.printf("createShip: %s (name=%s)%n", person, person.getName());
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }
}
