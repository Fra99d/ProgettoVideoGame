package com.example.videoGameBackend.controller;

import com.example.videoGameBackend.model.User;
import com.example.videoGameBackend.request.LoginRequest;
import com.example.videoGameBackend.request.RegisterRequest;
import com.example.videoGameBackend.service.JwtService;
import com.example.videoGameBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private JwtService JwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userService.register(request);
            return ResponseEntity.ok(Map.of("message", "Registrazione avvenuta con successo per: " + user.getUsername()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Errore durante la registrazione: " + e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.login(request);
            String token = JwtService.generateToken(user);
            return ResponseEntity.ok(Map.of(
                    "message", "Login effettuato con successo",
                    "token", token
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Errore durante il login: " + e.getMessage()));
        }
    }

}

