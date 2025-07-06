package com.example.videoGameBackend.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;
    @Column(name = "youtube_video_id")
    private String youtubeVideoId;

    private String genre;

    private String developer;

    private LocalDate releaseDate;

    private BigDecimal price;

    private String coverImageUrl;

    public Game() {}

    public Game(String title, String description, String genre, String developer,
                LocalDate releaseDate, BigDecimal price, String coverImageUrl) {
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.developer = developer;
        this.releaseDate = releaseDate;
        this.price = price;
        this.coverImageUrl = coverImageUrl;
    }

    // Getters e setters standard
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDeveloper() { return developer; }
    public void setDeveloper(String developer) { this.developer = developer; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCoverImageUrl() { return coverImageUrl; }
    public void setCoverImageUrl(String coverImageUrl) { this.coverImageUrl = coverImageUrl; }
    public String getYoutubeVideoId() { return youtubeVideoId; }
    public void setYoutubeVideoId(String youtubeVideoId) { this.youtubeVideoId = youtubeVideoId; }
}