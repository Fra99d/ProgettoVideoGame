package com.example.videoGameBackend.service;

import com.example.videoGameBackend.model.Game;
import com.example.videoGameBackend.model.User;
import com.example.videoGameBackend.repository.GameRepository;
import com.example.videoGameBackend.repository.UserRepository;
import com.example.videoGameBackend.request.LoginRequest;
import com.example.videoGameBackend.request.RegisterRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterRequest request) {
        // Controllo se username già esistente
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username già esistente");
        }
        // Controllo se email già registrata
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email già registrata");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user); // Salva e ritorna direttamente
    }

    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Username o password errati"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new IllegalArgumentException("Username o password errati");
        }

        return user;
    }
    public void addFavoriteGame(Long userId, Long gameId) {
        User user = userRepository.findByIdWithFavorites(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        user.getFavoriteGames().add(game);
        userRepository.save(user);
    }

    public void removeFavoriteGame(Long userId, Long gameId) {
        User user = userRepository.findByIdWithFavorites(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        user.getFavoriteGames().remove(game);
        userRepository.save(user);
    }

    public Set<Game> getFavoriteGames(Long userId) {
        User user = userRepository.findByIdWithFavorites(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getFavoriteGames();
    }
    public void addWishlistGame(Long userId, Long gameId) {
        User user = userRepository.findByIdWithWishlist(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        user.getWishlistGames().add(game);
        userRepository.save(user);
    }

    public void removeWishlistGame(Long userId, Long gameId) {
        User user = userRepository.findByIdWithWishlist(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found"));
        user.getWishlistGames().remove(game);
        userRepository.save(user);
    }

    public Set<Game> getWishlistGames(Long userId) {
        User user = userRepository.findByIdWithWishlist(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user.getWishlistGames();
    }


}
