import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';
import { UsuarioService } from 'src/app/usuario/usuario.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public auth: AuthService,
              private router: Router,
              private usuarioService: UsuarioService) { }

  ngOnInit() {
    if (this.auth.jwtPayload) {
      this.router.navigate(['index']);
    }
  }

  login(username, password) {
    this.auth.login(username, password)
    .then(resp => {
      this.usuarioService.findUsuarioPorEmail(username).subscribe(
        response => {
          this.auth.advogado = response.advogado;
        }
      );
      this.router.navigate(['index']);
    })
    .catch(error => {
      if (error.error.error === 'invalid_grant') {
        alert('Senha errada');
      } else {
        alert(error.error.error_description);
      }
    });
  }

}
