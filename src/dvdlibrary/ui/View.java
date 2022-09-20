package dvdlibrary.ui;

public interface View {
    public int display();
    public void displaySuccessMessage();
    public void displayErrorMessage(String error);
    public String getTitle();
    public String getContent();
}
