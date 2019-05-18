import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { RouterModule } from '@angular/router';
import { AuthService } from './auth.service';
import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { LogoutService } from './logout.service';
import { EsqueciSenhaComponent } from './esqueci-senha/esqueci-senha.component';
import { DialogService } from 'primeng/api';

export function tokenGetter() {
  return localStorage.getItem('token');
}

@NgModule({
  providers: [AuthService, JwtHelperService, LogoutService, DialogService],
  declarations: [LoginComponent, EsqueciSenhaComponent],
  entryComponents: [EsqueciSenhaComponent],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    InputTextModule,
    ButtonModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: tokenGetter
      }
    }),
  ]
})
export class SegurancaModule { }
