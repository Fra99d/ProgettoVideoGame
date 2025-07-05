import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
interface Game {
  id: number;
  title: string;
  genre: string;
  coverImageUrl: string;
  description: string;
  developer: string;
  price: number;
  youtubeVideoId: string;
}

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
  wishlistGames: Game[] = [];

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    this.loadWishlist();
  }

  loadWishlist() {
    this.http.get<Game[]>('http://localhost:8080/api/wishlist', {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    }).subscribe({
      next: (games) => this.wishlistGames = games,
      error: (err) => console.error('Errore caricando la wishlist:', err)
    });
  }

  removeFromWishlist(gameId: number) {
    this.http.delete<void>(`http://localhost:8080/api/wishlist/remove/${gameId}`, {
      headers: { Authorization: 'Bearer ' + localStorage.getItem('token') }
    }).subscribe({
      next: () => this.loadWishlist(),
      error: (err) => console.error('Errore rimuovendo dalla wishlist:', err)
    });
  }
}
