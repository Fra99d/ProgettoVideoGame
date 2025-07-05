package com.example.videoGameBackend.repository;

import com.example.videoGameBackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.favoriteGames WHERE u.id = :id")
    Optional<User> findByIdWithFavorites(@Param("id") Long id);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.wishlistGames WHERE u.id = :id")
    Optional<User> findByIdWithWishlist(@Param("id") Long id);


}


