package classroster;

import classroster.dao.DAOImplMemory;
import classroster.ui.UserIOImplConsole;
import classroster.ui.View;

public class App {

    private static final Controller CONTROLLER = new Controller(
        new DAOImplMemory(),
        new View(new UserIOImplConsole())
    );

    public static void main(String[] args) {
        CONTROLLER.run();
    }
}
