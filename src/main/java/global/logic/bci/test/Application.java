package global.logic.bci.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import global.logic.bci.test.utils.DatabaseBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private DatabaseBuilder databaseBuilder;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		databaseBuilder.generateDatabase();
	}
}
