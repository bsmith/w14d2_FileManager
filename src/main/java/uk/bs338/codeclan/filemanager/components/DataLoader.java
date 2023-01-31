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

@Profile("!test") //Run every time EXCEPT Tests
@ConditionalOnExpression("${dataLoaderEnabled:false}")
@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    FileRepository fileRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    FolderRepository folderRepository;


    public DataLoader() {

    }

    public void run(ApplicationArguments args) {
        System.out.println("*** RUNNING THE DATA LOADER ***");

        fileRepository.deleteAll();
        folderRepository.deleteAll();
        personRepository.deleteAll();

        Person dutchman = new Person("The Flying Dutchman");
        personRepository.save(dutchman);

        Person pearl = new Person("The Black Pearl");
        personRepository.save(pearl);

        Person blackPig = new Person("The Black Pig");
        personRepository.save(blackPig);

        Person dustman = new Person("The Flying Dustman");
        personRepository.save(dustman);

        Person galley = new Person("Adventure Galley");
        personRepository.save(galley);

        Person revenge = new Person("Queen Anne's Revenge");
        personRepository.save(revenge);

        Person fancy = new Person("Fancy");
        personRepository.save(fancy);

        Person fortune = new Person("Royal Fortune");
        personRepository.save(fortune);

        File jack = new File("Jack", "Sparrow", 32, pearl);
        fileRepository.save(jack);

        File john = new File("John", "Silver", 55, dutchman);
        fileRepository.save(john);

        File pugwash = new File("Horatio", "Pugwash", 55, blackPig);
        fileRepository.save(pugwash);

        File maggie = new File("Maggie", "Lafayette", 35, dustman);
        fileRepository.save(maggie);

        File william = new File("William", "Kidd", 40, galley);
        fileRepository.save(william);

        File blackbeard = new File("Edward", "Teach", 45, revenge);
        fileRepository.save(blackbeard);

        File henry = new File("Henry", "Avery", 25, fancy);
        fileRepository.save(henry);

        File bart = new File("Bartholomew", "Roberts", 47, fortune);
        fileRepository.save(bart);


        Folder folder1 = new Folder("Tortuga", 100);
        folderRepository.save(folder1);

        Folder folder2 = new Folder("Treasure Island", 690);
        folderRepository.save(folder2);

        Folder folder3 = new Folder("Barbados", 500);
        folderRepository.save(folder3);

        Folder folder4 = new Folder("St. Kitts", 500);
        folderRepository.save(folder4);

        Folder folder5 = new Folder("Havana", 200);
        folderRepository.save(folder5);

        Folder folder6 = new Folder("Port Royal", 1000);
        folderRepository.save(folder6);

        jack.addRaid(folder1);
        jack.addRaid(folder2);
        fileRepository.save(jack);

        folder2.addPirate(john);
        folderRepository.save(folder2);

        folder3.addPirate(pugwash);
        folder3.addPirate(maggie);
        folderRepository.save(folder3);

        folder4.addPirate(pugwash);
        folder3.addPirate(jack);
        folderRepository.save(folder4);

        blackbeard.addRaid(folder5);
        blackbeard.addRaid(folder6);
        fileRepository.save(blackbeard);

        folder5.addPirate(william);
        folderRepository.save(folder5);

        folder6.addPirate(henry);
        folderRepository.save(folder6);

        System.out.println("*** FINISHED THE DATA LOADER ***");
    }
}