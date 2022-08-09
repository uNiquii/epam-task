package dev.epamtask;

import dev.epamtask.model.Contact;
import dev.epamtask.model.User;
import dev.epamtask.parser.ParseDate;
import dev.epamtask.repository.UserRepository;
import dev.epamtask.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataAccessApplication implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(DataAccessApplication.class);
	@Autowired
	UserRepository userRepository;
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(DataAccessApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("StartApplication...");
		startUsersApp();

	}

	void startUsersApp() {
		testItAll();
		userService.getUserOptions();


	}

	void testItAll() {
		log.info("[TESTING ALL FUNCTIONS]");
		log.info("[Create user ]");
		User user = new User("Patrik", "Gelnar", new Contact(1,"city","email",774452435,77, ParseDate.parseDate("2022-02-14"), "address"));
		userService.createTestUser(user);

		log.info("[Create contact ]");
		Contact contact2 = new Contact(2,"Praha","test@test.cz",999999999,77, ParseDate.parseDate("2022-02-14"), "address");
		Contact contact3 = new Contact(3,"Praha","test2@test2.cz",888888888,10, ParseDate.parseDate("2000-05-20"), "address");
		Contact contact4 = new Contact(4,"Ostrava","test3@test3.cz",777777777,10, ParseDate.parseDate("1993-02-14"), "address");

		log.info("[Create users]");
		User user2 = new User("Michaela", "Kleckova", contact2);
		User user3 = new User("Arnold", "Lojza", contact3);
		User user4 = new User("Mary", "Glue", contact4);
		userService.createTestUser(user2);
		userService.createTestUser(user3);
		userService.createTestUser(user4);

		log.info("[Get one user ]");
		System.out.println(userRepository.findById(1));
		log.info("[ Get all users]");
		userRepository.list().forEach(System.out::println);
		log.info("[ Get all users with contacts ]");
		userRepository.listWithContact().forEach(System.out::println);


		Integer age = 10;
		log.info("[ Get all contacts with required age " + age + " ]");
		userRepository.withRequiredAge(age).forEach(System.out::println);

		Integer phoneNumber = 777777777;
		log.info("[ Get all contacts with required phone number " + phoneNumber + " ]");
		userRepository.withRequiredPhoneNumber(phoneNumber).forEach(System.out::println);

		String email = "test2@test2.cz";
		log.info("[ Get all contacts with required email " + email + " ]");
		userRepository.withRequiredEmail(email).forEach(System.out::println);

		String city = "Ostrava";
		log.info("[ Get all contacts with provided city " + city + " ]");
		userRepository.withRequiredCity(city).forEach(System.out::println);

		// todo
		//log.info("[ Test to change User1 CITY to BANIK ]");
		//User user5 = userRepository.findById(1);
		//user5.getContact().setCity("BANIK");
		//userRepository.update(user5);
		//userRepository.listWithContact().forEach(System.out::println);

		log.info("[TEST FINISHED]");


	}
}
