import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Game } from '../models/game.model';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class FavoriteService {
  private apiUrl = `${environment.apiUrl}/favorites`;
  constructor(private http: HttpClient) {}

  getFavorites(): Observable<Game[]> {
    return this.http.get<Game[]>(this.apiUrl);
  }

  addFavorite(gameId: number): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add/${gameId}`, {});
  }

  removeFavorite(gameId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/remove/${gameId}`);
  }
}
