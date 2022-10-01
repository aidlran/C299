package c299.flooringmastery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import c299.flooringmastery.controller.Controller;

public class App {

	static ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

	public static void main(String[] args) {
		appContext.getBean("coreController", Controller.class).run();
	}
}
