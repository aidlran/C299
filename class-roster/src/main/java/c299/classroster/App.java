package c299.classroster;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml")) {
            appContext.getBean("controller", Controller.class).run();
        }
    }
}
