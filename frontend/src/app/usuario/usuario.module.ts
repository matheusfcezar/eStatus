import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioCadastroComponent } from './usuario-cadastro/usuario-cadastro.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { DropdownModule } from 'primeng/dropdown';
import { UsuarioService } from './usuario.service';

@NgModule({
  providers: [UsuarioService],
  declarations: [UsuarioCadastroComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    UsuarioRoutingModule,
    RouterModule,
    InputTextModule,
    ButtonModule,
    DropdownModule,

  ]
})
export class UsuarioModule { }
