package classroster.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import classroster.Student;

public class DAOImplFile extends DAOImplMemory {

	private static final String ROSTER_FILE = "roster.txt";
	private static final String DELIMITER = "::";

	private void readRoster() throws DAOException {

		File file = new File(ROSTER_FILE);

		if (!file.exists()) try {
			file.createNewFile();
			return;
		} catch (IOException e) {
			throw new DAOException("Could not create roster file.", e);
		}

		Scanner in;

		try {
			in = new Scanner(new BufferedReader(new FileReader(ROSTER_FILE)));
		} catch (FileNotFoundException e) {
			throw new DAOException("Could not access roster file.", e);
		}

		STUDENTS.clear();

		Student currentStudent;

		while (in.hasNextLine()) {
			currentStudent = unmarshallStudent(in.nextLine());
			STUDENTS.put(currentStudent.getID(), currentStudent);
		}

		in.close();
	}

	private void writeRoster() throws DAOException {

		PrintWriter out;

		try {
			out = new PrintWriter(new FileWriter(ROSTER_FILE));
		} catch (IOException e) {
			throw new DAOException("Could not write to roster file.", e);
		}

		for (Student student : super.getAllStudents()) {
			out.println(marshallStudent(student));
			out.flush();
		}

		out.close();
	}

	private Student unmarshallStudent(String encodedStudent) {
		String[] tokens = encodedStudent.split(DELIMITER);
		Student student = new Student(tokens[0]);
		student.setFirstName(tokens[1]);
		student.setLastName(tokens[2]);
		student.setCohort(tokens[3]);
		return student;
	}

	private String marshallStudent(Student student) {
		return student.getID() + DELIMITER + student.getFirstName() + DELIMITER + student.getLastName() + DELIMITER + student.getCohort();
	}

	@Override
	public Student getStudent(String id) throws DAOException {
		readRoster();
		return super.getStudent(id);
	}

	@Override
	public Student addStudent(Student student) throws DAOException {
		readRoster();
		Student newStudent = super.addStudent(student);
		if (newStudent == null) writeRoster();
		return newStudent;
	}

	@Override
	public Student removeStudent(String id) throws DAOException {
		readRoster();
		Student removedStudent = super.removeStudent(id);
		if (removedStudent != null) writeRoster();
		return removedStudent;
	}

	@Override
	public List<Student> getAllStudents() throws DAOException {
		readRoster();
		return super.getAllStudents();
	}
}
