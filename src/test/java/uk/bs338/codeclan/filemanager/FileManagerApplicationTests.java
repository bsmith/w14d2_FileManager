package uk.bs338.codeclan.filemanager;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import uk.bs338.codeclan.filemanager.models.File;
import uk.bs338.codeclan.filemanager.models.Folder;
import uk.bs338.codeclan.filemanager.models.Person;
import uk.bs338.codeclan.filemanager.repositories.FileRepository;
import uk.bs338.codeclan.filemanager.repositories.FolderRepository;
import uk.bs338.codeclan.filemanager.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class FileManagerApplicationTests {

	@Autowired
	FileRepository fileRepository;

	@Autowired
	PersonRepository personRepository;

	@Autowired
	FolderRepository folderRepository;

	@Test
	public void contextLoads() {
		System.out.printf("BREAKPOINT%n");
	}


}
