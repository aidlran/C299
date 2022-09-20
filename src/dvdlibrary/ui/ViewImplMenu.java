package dvdlibrary.ui;

import java.util.HashMap;
import java.util.Map;

import io.UserIO;

public abstract class ViewImplMenu implements View {

    protected UserIO io;
    protected Map<String, String> choices = new HashMap<>();

    public ViewImplMenu(UserIO io) {
        this.io = io;
    }

    public void displaySuccessMessage() {
        io.displaySuccessMessage();
    }

    public void displayErrorMessage(String error) {
        io.displayErrorMessage(error);
    }

    public int display() {

        String underline = "";

        for (int i = 0; i < getTitle().length(); i++)
            underline += '=';

        io.print("\n" + underline);
        io.print(getTitle());
        io.print(underline);
        io.print("\n" + getContent() + "\n");
    
        return io.readInt("Enter your choice");
    }
}
