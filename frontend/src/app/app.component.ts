import { Component, OnInit } from '@angular/core';
import { AuthService } from './seguranca/auth.service';
import { LogoutService } from './seguranca/logout.service';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/components/common/menuitem';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'eStatus';
  items: MenuItem[];

  constructor(private auth: AuthService,
              private logoutService: LogoutService,
              private router: Router) {}

  ngOnInit() {
    this.items = [
      {label: 'Sair', icon: 'pi pi-fw pi-power-off', command: () => this.logout() }
    ];
  }

  get usuarioLogado() {
    return this.auth.jwtPayload;
  }

  get nomeUsuario() {
    if (this.auth.jwtPayload) {
      return this.auth.jwtPayload.nome;
    }
    return '';
  }

  logout() {
    this.logoutService.logout()
      .finally(() => {
        this.router.navigate(['/']);
      });
  }
}
