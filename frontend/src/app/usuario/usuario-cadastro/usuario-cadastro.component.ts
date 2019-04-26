import { Component, OnInit } from '@angular/core';

import { Usuario } from '../../model';
import { UsuarioService } from '../usuario.service';
import { Route, Router } from '@angular/router';
import { TrustedStyleString } from '@angular/core/src/sanitization/bypass';

@Component({
  selector: 'app-usuario-cadastro',
  templateUrl: './usuario-cadastro.component.html',
  styleUrls: ['./usuario-cadastro.component.css']
})
export class UsuarioCadastroComponent implements OnInit {

  usuario = new Usuario();
  oab: string;
  ufOab: string;
  ufs = [];

  constructor(private usuarioService: UsuarioService, private router: Router) { }

  ngOnInit() {
    this.ufs = ['AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO',
     'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN',
      'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'];

  }

  cadastrar() {
    this.usuario.oab = `${this.oab}/${this.ufOab}`;
    this.usuarioService.cadastrar(this.usuario)
      .subscribe(
        resp => {
          this.router.navigate(['/']);
          alert('Seu cadastro foi efetuado com sucesso. Acesse seu e-mail para ativar seu usuário.');
        },
        error => {
          alert('Erro ao realizar cadastro. Verifique se seus dados estão inseridos corretamente.');
        },
        () => console.log('finally'));
  }

}
