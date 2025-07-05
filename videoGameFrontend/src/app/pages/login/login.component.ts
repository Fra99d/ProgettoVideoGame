import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  username = '';
  password = '';
  message = '';
  isError = false;

  regUsername = '';
  regEmail = '';
  regPassword = '';
  regMessage = '';
  regIsError = false;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.message = '';
    this.isError = false;

    if (!this.username || !this.password) {
      this.message = 'Compila tutti i campi.';
      this.isError = true;
      return;
    }

    this.authService.login(this.username, this.password).subscribe({
      next: (response) => {
        console.log('Login response:', response);

        // Salva il token JWT solo se è presente
        if (response && response.token) {
          localStorage.setItem('token', response.token);
        } else {
          console.error('Token mancante nella risposta!');
        }

        // Redirect alla home
        this.router.navigate(['/home']);
      },
      error: (err) => {
        console.error(err);
        this.isError = true;
        this.message = err.status === 401
          ? 'Credenziali non valide.'
          : 'Errore durante il login. Riprova.';
      }
    });
  }

  register() {
    this.regMessage = '';
    this.regIsError = false;

    if (!this.regUsername || !this.regEmail || !this.regPassword) {
      this.regMessage = 'Compila tutti i campi.';
      this.regIsError = true;
      return;
    }

    this.authService.register({ username: this.regUsername, email: this.regEmail, password: this.regPassword }).subscribe({
      next: () => {
        this.regIsError = false;
        this.regMessage = 'Registrazione completata! Ora puoi accedere.';
      },
      error: (err) => {
        console.error(err);
        this.regIsError = true;
        this.regMessage = err.status === 400
          ? 'Username o email già in uso.'
          : 'Errore durante la registrazione. Riprova.';
      }
    });
  }
}
