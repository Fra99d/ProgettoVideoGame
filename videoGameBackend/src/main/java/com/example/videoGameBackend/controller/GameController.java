package com.example.videoGameBackend.controller;


import com.example.videoGameBackend.model.Game;
import com.example.videoGameBackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.videoGameBackend.dao.GameDAO;

import java.util.List;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {


    @Autowired
    private GameDAO gameDAO;

    @GetMapping
    public List<Game> getAllGames() {
        return gameDAO.findAll();
    }

    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameDAO.save(game);
    }
}
