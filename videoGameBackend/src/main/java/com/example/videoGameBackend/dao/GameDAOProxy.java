package com.example.videoGameBackend.dao;

import com.example.videoGameBackend.model.Game;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class GameDAOProxy implements GameDAO {

    private final GameDAO gameDAO;

    public GameDAOProxy(GameDAOImpl gameDAOImpl) {
        this.gameDAO = gameDAOImpl;
    }

    @Override
    public List<Game> findAll() {
        System.out.println("[PROXY] Logging: sto per chiamare findAll()");
        List<Game> games = gameDAO.findAll();
        System.out.println("[PROXY] Logging: chiamata completata. Ho trovato " + games.size() + " giochi.");
        return games;
    }

    @Override
    public Game save(Game game) {
        System.out.println("[PROXY] Logging: sto per salvare un nuovo gioco: " + game.getTitle());
        return gameDAO.save(game);
    }
}

