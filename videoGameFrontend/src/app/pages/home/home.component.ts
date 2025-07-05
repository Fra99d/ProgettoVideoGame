import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

interface Game {
  id: number;
  title: string;
  genre: string;
  coverImageUrl: string;
  description: string;
  developer: string;
  price: number;
  release_date: string;
  youtubeVideoId: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  games: Game[] = [];
  filteredGames: Game[] = [];
  searchTerm: string = '';
  favoriteGameIds: Set<number> = new Set<number>();
  wishlistGameIds: Set<number> = new Set<number>();
  selectedGame: Game | null = null;
  videoUrl: SafeResourceUrl | null = null;

  constructor(private http: HttpClient, private sanitizer: DomSanitizer) {}

  ngOnInit(): void {
    this.http.get<Game[]>('http://localhost:8080/api/games').subscribe({
      next: data => {
        this.games = data;
        this.filteredGames = data;
      },
      error: err => console.error(err)
    });

    this.http.get<Game[]>('http://localhost:8080/api/favorites', {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    }).subscribe({
      next: (games) => this.favoriteGameIds = new Set(games.map(g => g.id)),
      error: (err) => console.error('Errore caricando i preferiti:', err)
    });
    this.http.get<Game[]>('http://localhost:8080/api/wishlist', {
          headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
        }).subscribe({
          next: (games) => this.wishlistGameIds = new Set(games.map(g => g.id)),
          error: (err) => console.error('Errore caricando la wishlist:', err)
        });
  }

  filterGames(): void {
    this.filteredGames = this.games.filter(game =>
      game.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      game.genre.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  toggleFavorite(gameId: number) {
    if (this.favoriteGameIds.has(gameId)) {
      this.http.delete<void>(`http://localhost:8080/api/favorites/remove/${gameId}`, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
      }).subscribe({
        next: () => this.favoriteGameIds.delete(gameId),
        error: (err) => console.error('Errore rimuovendo dai preferiti:', err)
      });
    } else {
      this.http.post<void>(`http://localhost:8080/api/favorites/add/${gameId}`, {}, {
        headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
      }).subscribe({
        next: () => this.favoriteGameIds.add(gameId),
        error: (err) => console.error('Errore aggiungendo ai preferiti:', err)
      });
    }
  }
  toggleWishlist(gameId: number) {
      if (this.wishlistGameIds.has(gameId)) {
        this.http.delete<void>(`http://localhost:8080/api/wishlist/remove/${gameId}`, {
          headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
        }).subscribe({
          next: () => this.wishlistGameIds.delete(gameId),
          error: (err) => console.error('Errore rimuovendo dalla wishlist:', err)
        });
      } else {
        this.http.post<void>(`http://localhost:8080/api/wishlist/add/${gameId}`, {}, {
          headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
        }).subscribe({
          next: () => this.wishlistGameIds.add(gameId),
          error: (err) => console.error('Errore aggiungendo alla wishlist:', err)
        });
      }
  }

  openGamePopup(game: Game) {
    this.selectedGame = game;
    const url = this.getYoutubeEmbedUrl(game.youtubeVideoId);
    this.videoUrl = this.sanitizer.bypassSecurityTrustResourceUrl(url);
  }

  closePopup() {
    this.selectedGame = null;
    this.videoUrl = null;
  }

  getYoutubeEmbedUrl(videoId: string): string {
    return `https://www.youtube.com/embed/${videoId}`;
  }
}
