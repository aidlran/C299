package dvdlibrary;

import util.UserIO;

public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    public void displaySuccessMessage() {
        io.displaySuccessMessage();
    }

    public void displayErrorMessage(String error) {
        io.displayErrorMessage(error);
    }
}
