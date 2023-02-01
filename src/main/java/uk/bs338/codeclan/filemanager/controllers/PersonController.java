package uk.bs338.codeclan.filemanager.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.models.Person;
import uk.bs338.codeclan.filemanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    @Autowired
    PersonRepository personRepository;

    @GetMapping
    public ResponseEntity<List<Person>> getAllPersons() {
        System.out.printf("Route: getAllPersons!%n");
        return new ResponseEntity<>(personRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Person>> getOnePerson(@PathVariable Long id) {
        System.out.printf("Route: getOnePerson id=%s%n", id);
        Optional<Person> person = personRepository.findById(id);
        System.out.printf("  person=%s id=%s%n", person, person.map(Person::getId));
        return new ResponseEntity<>(person, person.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        System.out.printf("createPerson: %s (name=%s)%n", person, person.getName());
        personRepository.save(person);
        return new ResponseEntity<>(person, HttpStatus.CREATED);
    }

    @GetMapping("{id}/files")
    @ResponseStatus(HttpStatus.OK)
    public List<File> listFilesOfPerson(@PathVariable Long id) {
        ArrayList<File> files = new ArrayList<>();
        Person person = personRepository.findById(id).get();
        for (Folder folder : person.getFolders())
            files.addAll(folder.getFiles());
        return files;
    }

//    @GetMapping(value = "/ships/{id}/pirates")
//    public ResponseEntity<List<File>> getPiratesOnShip(@PathVariable Long id) {
//        Optional<Person> ship = personRepository.findById(id);
//        return new ResponseEntity<>(ship.map(Person::getPirates).orElseGet(() -> new ArrayList<>()),
//                ship.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
//    }

}
