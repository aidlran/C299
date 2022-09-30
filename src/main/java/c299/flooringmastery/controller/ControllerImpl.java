package c299.flooringmastery.controller;

import c299.flooringmastery.service.ServiceLayer;
import c299.flooringmastery.ui.ViewImpl;
import c299.io.UserIO;

public abstract class ControllerImpl implements Controller {

	ViewImpl view;
	UserIO userIO;
	ServiceLayer service;

	public ControllerImpl(ServiceLayer service, UserIO userIO, ViewImpl view) {
		this.view = view;
		this.userIO = userIO;
		this.service = service;
	}

    public Controller run() {
        view.render();
        return null;
    }
}
