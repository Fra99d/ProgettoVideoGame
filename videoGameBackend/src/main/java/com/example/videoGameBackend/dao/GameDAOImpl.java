package com.example.videoGameBackend.dao;

import com.example.videoGameBackend.model.Game;
import com.example.videoGameBackend.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GameDAOImpl implements GameDAO {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> findAll() {
        return gameRepository.findAll();
    }
    @Override
    public Game save(Game game) {
        return gameRepository.save(game);
    }
}
