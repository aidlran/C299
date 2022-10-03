package c299.guessthenumber.controller;

public interface MenuController {
    /**
     * Runs the MenuController.
     * @return A MenuController - either itself (this)
     * or a new MenuController if there is a change.
     * If the return is null, that means "exit".
     */
    MenuController run();
}
