package c299.guessthenumber.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import c299.guessthenumber.dto.Game;
import c299.guessthenumber.service.ServiceLayer;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private ServiceLayer service;

    /**
     * POST /api/game
     * Creates a new game and return the game ID in the body.
     * @return The new Game's ID.
     */
    @PostMapping("game")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame() {
        return service.createGame();
    }

    /**
     * GET /api/game
     * @return List of all existing Games.
     */
    @GetMapping("game")
    public List<Game> getAllGames() {
        return service.getAllGames();
    }

    /**
     * Gets an existing Game by ID.
     * The answer is removed if the game is still in progress.
     * @param id A Game ID.
     * @return
     */
    @GetMapping("game/{id}")
    public Game getGame(@PathVariable int id) {
        Game game = service.getGame(id);
        if (game == null) throw new NotFoundException();
        return game;
    }
}
