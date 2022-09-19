package classroster.ui;

import java.util.List;

import classroster.dto.Student;

public class View {

    private UserIO io;

    public View(UserIO io) {
        this.io = io;
    }

    private void displayBanner(String label) {
        io.print("\n=== " + label + " ===");
    }

    public void displaySuccessMessage() {
        io.displaySuccessMessage();
    }

    public void displayErrorMessage(String error) {
        io.displayErrorMessage(error);
    }

    public int displayMenu() {

        io.print(
            "\nMAIN MENU\n" +
            "=========\n\n" +

            "1) List student IDs\n" +
            "2) View a student\n" +
            "3) Add a new student\n" +
            "4) Remove a student\n\n" +

            "0) Exit\n"
        );

        return io.readInt("Please select your choice", 0, 4);
    }

    public Student getNewStudent() {
        displayBanner("New Student");
        Student newStudent = new Student(io.readString("Student ID"));
        newStudent.setFirstName(io.readString("Student's first name"));
        newStudent.setLastName(io.readString("Student's last name"));
        newStudent.setCohort(io.readString("Student's cohort"));
        return newStudent;
    }

    public String getStudentIDChoice() {
        return io.readString("Enter a student ID");
    }

    public void displayStudent(Student student) {
        if (student == null) displayErrorMessage("No such student.");
        else {
            io.print(
                student.getID() + "\n" +
                student.getFirstName() + " " + student.getLastName() + "\n" +
                student.getCohort()
            );
        }
    }

    public void displayStudentList(List<Student> studentList) {
        displayBanner("All Students");
        for (Student student : studentList) {
            io.print(String.format("#%s : %s %s",
                student.getID(),
                student.getFirstName(),
                student.getLastName()
            ));
        }
    }
}
