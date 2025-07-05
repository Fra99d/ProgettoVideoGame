package com.example.videoGameBackend.repository;

import com.example.videoGameBackend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {}
