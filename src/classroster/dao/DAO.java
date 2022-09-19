package classroster.dao;

import java.util.List;

import classroster.Student;

public interface DAO {

    /**
     * @param id The ID of a Student in the roster.
     * @return A Student object or NULL if no such Student exists.
     */
    Student getStudent(String id) throws DAOException;

    /**
     * Adds a Student to the roster.
     * @param student A Student to add to the roster.
     * @return If a Student object is already associated with the ID, it is
     * returned, otherwise NULL is returned.
     */
    Student addStudent(Student student) throws DAOException;

    /**
     * Removes the Student associated with the ID given from the roster.
     * @param id A student ID.
     * @return If the Student exists in the roster and is removed, it is returned,
     * otherwise NULL is returned.
     */
    Student removeStudent(String id) throws DAOException;

    /**
     * @return A List object with all Students in the roster.
     */
    List<Student> getAllStudents() throws DAOException;
}
