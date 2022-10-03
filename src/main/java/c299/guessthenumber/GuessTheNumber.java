package c299.guessthenumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import c299.guessthenumber.controller.MenuController;

@SpringBootApplication
public class GuessTheNumber implements CommandLineRunner {

	@Autowired
	private MenuController controller;

	public static void main(String[] args) {
		SpringApplication.run(GuessTheNumber.class, args);
	}

	@Override
	public void run(String ...args) {
		// Continuously run & update the active controller
		// until `null` is returned
		do controller = controller.run();
		while (controller != null);
	}
}
