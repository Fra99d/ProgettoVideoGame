package com.example.videoGameBackend.controller;

import com.example.videoGameBackend.model.Game;
import com.example.videoGameBackend.service.JwtService;
import com.example.videoGameBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/add/{gameId}")
    public ResponseEntity<?> addFavorite(@PathVariable Long gameId, @RequestHeader("Authorization") String token) {
        Long userId = jwtService.extractUserId(token.substring(7));
        userService.addFavoriteGame(userId, gameId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove/{gameId}")
    public ResponseEntity<?> removeFavorite(@PathVariable Long gameId, @RequestHeader("Authorization") String token) {
        Long userId = jwtService.extractUserId(token.substring(7));
        userService.removeFavoriteGame(userId, gameId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Set<Game>> getFavorites(@RequestHeader("Authorization") String token) {
        Long userId = jwtService.extractUserId(token.substring(7));
        return ResponseEntity.ok(userService.getFavoriteGames(userId));
    }
}