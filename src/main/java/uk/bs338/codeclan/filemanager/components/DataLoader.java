package uk.bs338.codeclan.filemanager.components;

import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.models.Person;
import uk.bs338.codeclan.filemanager.repositories.FileRepository;
import uk.bs338.codeclan.filemanager.repositories.FolderRepository;
import uk.bs338.codeclan.filemanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Profile("!test") //Run every time EXCEPT Tests
@ConditionalOnExpression("${dataLoaderEnabled:false}")
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    FolderRepository folderRepository;

    @Autowired
    PersonRepository personRepository;

    public DataLoader() {
    }

    public void run(ApplicationArguments args) {
        System.out.println("*** RUNNING THE DATA LOADER ***");

        fileRepository.deleteAll();
        folderRepository.deleteAll();
        personRepository.deleteAll();

        Person mainPerson = new Person("main");
        Person testPerson = new Person("test");
        List<Person> people = Arrays.asList(mainPerson, testPerson);
        personRepository.saveAll(people);

        Folder testFolder = new Folder("tests", testPerson);
        folderRepository.save(testFolder);

        fileRepository.save(new File("ApplicationTests", "java", 100, testFolder));

        Folder componentsFolder = new Folder("components", mainPerson);
        folderRepository.save(componentsFolder);

        fileRepository.save(new File("DataLoader", "java", 500, componentsFolder));

        Folder controllersFolder = new Folder("controllers", mainPerson);
        folderRepository.save(controllersFolder);

        fileRepository.save(new File("FileController", "java", 50, controllersFolder));
        fileRepository.save(new File("FolderController", "java", 51, controllersFolder));
        fileRepository.save(new File("PersonController", "java", 52, controllersFolder));

        Folder modelsFolder = new Folder("models", mainPerson);
        folderRepository.save(modelsFolder);

        fileRepository.save(new File("File", "java", 70, modelsFolder));
        fileRepository.save(new File("Folder", "java", 71, modelsFolder));
        fileRepository.save(new File("Person", "java", 72, modelsFolder));

        Folder repositoriesFolder = new Folder("repositories", mainPerson);
        folderRepository.save(repositoriesFolder);

        fileRepository.save(new File("FileRepository", "java", 80, repositoriesFolder));
        fileRepository.save(new File("FolderRepository", "java", 81, repositoriesFolder));
        fileRepository.save(new File("PersonRepository", "java", 82, repositoriesFolder));

        Folder toplevelFolder = new Folder("", mainPerson);
        folderRepository.save(toplevelFolder);

        fileRepository.save(new File("Application", "java", 200, toplevelFolder));

        System.out.println("*** FINISHED THE DATA LOADER ***");
    }
}