package com.example.videoGameBackend;

import com.example.videoGameBackend.model.Game;
import com.example.videoGameBackend.repository.GameRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
public class VideoGameBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoGameBackendApplication.class, args);
	}
	/*
	@Bean
	CommandLineRunner initDatabase(GameRepository gameRepository) {
		return args -> {
			if (gameRepository.count() == 0) { // evita duplicati ad ogni riavvio
				gameRepository.save(new Game(
						"Elden Ring",
						"Un epico action RPG open world.",
						"Action RPG",
						"FromSoftware",
						LocalDate.of(2022, 2, 25),
						new BigDecimal("59.99"),
						"http://localhost:8080/game/elden1.jpg"
				));

				gameRepository.save(new Game(
						"God of War Ragnarök",
						"Kratos e Atreus affrontano il destino.",
						"Action Adventure",
						"Santa Monica Studio",
						LocalDate.of(2022, 11, 9),
						new BigDecimal("69.99"),
						"http://localhost:8080/game/god1.jpg"
				));

				gameRepository.save(new Game(
						"Hogwarts Legacy",
						"Un'avventura magica nel mondo di Harry Potter.",
						"RPG",
						"Avalanche Software",
						LocalDate.of(2023, 2, 10),
						new BigDecimal("59.99"),
						"http://localhost:8080/game/ho1.jpg"
				));
				gameRepository.save(new Game(
						"The Witcher 3: Wild Hunt",
						"Un GDR open world acclamato con una storia profonda e un mondo immenso.",
						"Action RPG",
						"CD Projekt Red",
						LocalDate.of(2015, 5, 19),
						new BigDecimal("39.99"),
						"http://localhost:8080/game/wi1.jpg"
				));


				gameRepository.save(new Game(
						"Assassin's Creed Valhalla",
						"Vivi l’epopea vichinga in questo capitolo open world di Assassin’s Creed.",
						"Action Adventure",
						"Ubisoft",
						LocalDate.of(2020, 11, 10),
						new BigDecimal("59.99"),
						"http://localhost:8080/game/ass1.jpg"
				));

				gameRepository.save(new Game(
						"Red Dead Redemption 2",
						"Un western epico con un mondo dettagliatissimo e una storia coinvolgente.",
						"Action Adventure",
						"Rockstar Games",
						LocalDate.of(2018, 10, 26),
						new BigDecimal("49.99"),
						"http://localhost:8080/game/de1.jpg"
				));

				gameRepository.save(new Game(
						"Resident Evil Village",
						"L’ottavo capitolo horror della saga Resident Evil con atmosfere inquietanti.",
						"Survival Horror",
						"Capcom",
						LocalDate.of(2021, 5, 7),
						new BigDecimal("59.99"),
						"http://localhost:8080/game/re1.jpg"
				));


				System.out.println("✔️ Giochi di esempio inseriti nel database!");
			}
		};
	}

	 */
}
