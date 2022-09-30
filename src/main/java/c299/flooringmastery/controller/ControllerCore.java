package c299.flooringmastery.controller;

import c299.flooringmastery.dao.DAOException;
import c299.flooringmastery.service.ServiceLayer;
import c299.flooringmastery.ui.ViewImpl;
import c299.io.UserIO;

/**
 * A master controller that manages which controller is active.
 * There ended up being only one controller in use,
 * so this system isn't really utilised.
 */
public class ControllerCore extends ControllerImpl {

	Controller activeController;

	public ControllerCore(ServiceLayer service, UserIO userIO, ViewImpl view, Controller controller) {
		super(service, userIO, view);
		this.activeController = controller;
	}

	public Controller run() {

		loadAllData();

		do activeController = activeController.run();
		while (activeController != null);

		return activeController;
	}

	private void loadAllData() {
		try {
			service.loadAllData();
		}
		catch (DAOException e) {
			view.displayErrorMessage(e.getMessage());
		}
	}
}
