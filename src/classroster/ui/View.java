package classroster.ui;

public class View {

    private final UserIO IO = new UserIOConsole();

    public int displayMenu() {

        IO.print(
            "MAIN MENU\n" +
            "=========\n\n" +

            "1) List student IDs\n" +
            "2) Add a new student\n" +
            "3) View a student\n" +
            "4) Remove a student\n\n" +

            "0) Exit\n"
        );

        return IO.readInt("Please select your choice", 0, 4);
    }
}
