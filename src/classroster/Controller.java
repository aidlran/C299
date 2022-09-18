package classroster;

import classroster.ui.UserIO;
import classroster.ui.UserIOConsole;
import classroster.ui.View;

public class Controller {

    private final UserIO IO = new UserIOConsole();
    private final View VIEW = new View();

    public void run() {
        do {
            switch (VIEW.displayMenu()) {
                case 1:
                    // TODO
                    IO.print("-> List student IDs");
                    break;
                case 2:
                    // TODO
                    IO.print("-> Add a new student");
                    break;
                case 3:
                    // TODO
                    IO.print("-> View a student");
                    break;
                case 4:
                    // TODO
                    IO.print("-> Remove a student");
                    break;
                case 0:
                    return;
                default:
                    IO.print("Unknown command.");
            }
        } while (true);
    }
}
