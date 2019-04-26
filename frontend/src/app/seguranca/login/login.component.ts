import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(public auth: AuthService, private router: Router) { }

  ngOnInit() {
  }

  login(username, password) {
    this.auth.login(username, password)
    .then(resp => this.router.navigate(['index']))
    .catch(error => {
      if (error.error.error === 'invalid_grant') {
        alert('Senha errada');
      } else {
        alert(error.error.error_description);
      }
    });
  }

}
