package classroster;

import classroster.dao.DAOImplFile;
import io.UserIOImplConsole;

public class App {

    private static final Controller CONTROLLER = new Controller(
        new DAOImplFile(),
        new View(new UserIOImplConsole())
    );

    public static void main(String[] args) {
        CONTROLLER.run();
    }
}
