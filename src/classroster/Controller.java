package classroster;

import classroster.dao.DAO;
import classroster.ui.View;

public class Controller {

	private DAO dao;
	private View view;

	public Controller(DAO dao, View view) {
		this.dao = dao;
		this.view = view;
	}

	public void run() {
		do {
			switch (view.displayMenu()) {
				case 1:
					view.displayStudentList(dao.getAllStudents());
					break;
				case 2:
					view.displayStudent(dao.getStudent(view.getStudentIDChoice()));
					break;
				case 3:
					if (dao.addStudent(view.getNewStudent()) == null)
						view.displaySuccessMessage();
					else view.displayErrorMessage("A student with this ID already exists.");
					break;
				case 4:
					if (dao.removeStudent(view.getStudentIDChoice()) != null)
						view.displaySuccessMessage();
					else view.displayErrorMessage("No such student.");
					break;
				case 0:
					return;
				default:
					view.displayErrorMessage("Unknown command.");
			}
		} while (true);
	}
}
