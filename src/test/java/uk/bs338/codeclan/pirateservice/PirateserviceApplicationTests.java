package uk.bs338.codeclan.pirateservice;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;
import uk.bs338.codeclan.pirateservice.models.Pirate;
import uk.bs338.codeclan.pirateservice.models.Raid;
import uk.bs338.codeclan.pirateservice.models.Ship;
import uk.bs338.codeclan.pirateservice.repositories.PirateRepository;
import uk.bs338.codeclan.pirateservice.repositories.RaidRepository;
import uk.bs338.codeclan.pirateservice.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ActiveProfiles("test") //Indicates it's a test profile so will not run DataLoader
@SpringBootTest
public class PirateserviceApplicationTests {

	@Autowired
	PirateRepository pirateRepository;

	@Autowired
	ShipRepository shipRepository;

	@Autowired
	RaidRepository raidRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void createPirateAndShipThenSave(){

		Ship dutchman = new Ship("The Flying Dutchman");
		shipRepository.save(dutchman);
		Pirate jack = new Pirate("jack", "sparrow", 32, dutchman);
		System.out.printf("PirateserviceApplicationTests.createPirate: %s%n", jack);
		System.out.printf("  id=%s firstName=%s lastName=%s age=%s%n", jack.getId(), jack.getFirstName(), jack.getLastName(), jack.getAge());
		pirateRepository.save(jack);
		System.out.printf("PirateserviceApplicationTests.createPirate: %s%n", jack);
		System.out.printf("  id=%s firstName=%s lastName=%s age=%s%n", jack.getId(), jack.getFirstName(), jack.getLastName(), jack.getAge());
		System.out.printf("repo=%s%n", pirateRepository);
	}

	@Test
	public void createPirateAndRaidThenSave(){
		Ship pineapple = new Ship("The Flying PineApple");
		shipRepository.save(pineapple);
		Pirate spongy = new Pirate("Spongebob", "RoundPants", 42, pineapple);
		pirateRepository.save(spongy);

		Raid raid = new Raid("Tortuga", 100);
		raidRepository.save(raid);

		spongy.addRaid(raid);
		raid.addPirate(spongy);
		raidRepository.save(raid);
		
	}


}
