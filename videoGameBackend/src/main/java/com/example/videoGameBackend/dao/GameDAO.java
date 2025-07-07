package com.example.videoGameBackend.dao;

import com.example.videoGameBackend.model.Game;
import java.util.List;

public interface GameDAO {
    List<Game> findAll();
    Game save(Game game);
}
