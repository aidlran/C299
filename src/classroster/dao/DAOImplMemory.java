package classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import classroster.dto.Student;

/**
 * In memory DAO implementation.
 */
public class DAOImplMemory implements DAO {

	private final Map<String, Student> STUDENTS = new HashMap<>();

	@Override
	public Student getStudent(String id) {
		return STUDENTS.get(id);
	}

	@Override
	public Student addStudent(Student student) {
		Student existing = getStudent(student.getID());
		return existing != null ? existing : STUDENTS.put(student.getID(), student);
	}

	@Override
	public Student removeStudent(String id) {
		Student removed = STUDENTS.remove(id);
		return removed;
	}

	@Override
	public List<Student> getAllStudents() {
		return new ArrayList<Student>(STUDENTS.values());
	}
}
