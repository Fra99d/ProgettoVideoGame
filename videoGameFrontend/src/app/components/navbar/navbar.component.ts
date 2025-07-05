import { Component } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  show: boolean = false;

  constructor(private router: Router) {
    this.updateNavbarVisibility(this.router.url);

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateNavbarVisibility(event.urlAfterRedirects);
      }
    });
  }

  private updateNavbarVisibility(url: string) {
    const cleanUrl = url.split('?')[0].split('#')[0].replace(/\/+$/, '');
    console.log('[DEBUG] Navbar current URL:', cleanUrl);

    if (cleanUrl.startsWith('/login')) {
      this.show = false;
    } else if (cleanUrl.startsWith('/home') || cleanUrl.startsWith('/favorites')|| cleanUrl.startsWith('/wishlist')) {
      this.show = true;
    } else {
      this.show = false;
    }
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}

