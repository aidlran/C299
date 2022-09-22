package c299.classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import c299.classroster.Student;

/**
 * In memory DAO implementation.
 */
public class DAOImplMemory implements DAO {

	protected final Map<String, Student> STUDENTS = new HashMap<>();

	@Override
	public Student getStudent(String id) throws DAOException {
		return STUDENTS.get(id);
	}

	@Override
	public Student addStudent(Student student) throws DAOException {
		Student existing = getStudent(student.getID());
		return existing != null ? existing : STUDENTS.put(student.getID(), student);
	}

	@Override
	public Student removeStudent(String id) throws DAOException {
		Student removed = STUDENTS.remove(id);
		return removed;
	}

	@Override
	public List<Student> getAllStudents() throws DAOException {
		return new ArrayList<Student>(STUDENTS.values());
	}
}
