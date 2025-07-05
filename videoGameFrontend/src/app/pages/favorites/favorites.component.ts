import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

interface Game {
  id: number;
  title: string;
  genre: string;
  coverImageUrl: string;
  // altri campi se servono
}

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites.component.html',
  styleUrls: ['./favorites.component.css']
})
export class FavoritesComponent implements OnInit {
  favoriteGames: Game[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadFavorites();
  }
  loadFavorites() {
    this.http.get<Game[]>('http://localhost:8080/api/favorites', {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    }).subscribe({
      next: (games) => this.favoriteGames = games,
      error: (err) => console.error('Errore caricando i preferiti:', err)
    });
  }

  removeFavorite(gameId: number) {
    this.http.delete<void>(`http://localhost:8080/api/favorites/remove/${gameId}`, {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    }).subscribe({
      next: () => this.loadFavorites(),
      error: (err) => console.error('Errore rimuovendo il preferito:', err)
    });
  }
}
