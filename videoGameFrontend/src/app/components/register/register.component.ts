import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  username = '';
  email = '';
  password = '';
  message = '';     // messaggio di errore o successo
  isError = false; // indica se è un errore

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    this.message = '';
    this.isError = false;

    if (!this.username || !this.email || !this.password) {
      this.message = 'Compila tutti i campi.';
      this.isError = true;
      return;
    }

    this.authService.register({ username: this.username, email: this.email, password: this.password }).subscribe({
      next: () => {
        this.isError = false;
        this.message = 'Registrazione completata! Puoi accedere.';
        // opzionale: redirect automatico dopo qualche secondo
        // setTimeout(() => this.router.navigate(['/login']), 2000);
      },
      error: (err) => {
        console.error(err);
        this.isError = true;
        this.message = err.status === 400
          ? 'Username o email già in uso.'
          : 'Errore durante la registrazione. Riprova.';
      }
    });
  }
}

