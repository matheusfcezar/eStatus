import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Processo, Usuario } from 'src/app/model';
import { ProcessoService } from '../processo.service';
import { AuthService } from 'src/app/seguranca/auth.service';
import { DialogService } from 'primeng/api';
import { AddUsuarioComponent } from '../add-usuario/add-usuario.component';
import { CadastroProcessoComponent } from '../cadastro-processo/cadastro-processo.component';

@Component({
  selector: 'app-edit-processo',
  templateUrl: './edit-processo.component.html',
  styleUrls: ['./edit-processo.component.css'],
  providers: [DialogService]
})
export class EditProcessoComponent implements OnInit {

  usuariosSelecionados = [];
  processo: Processo;
  usuarios: Usuario[];

  constructor(public auth: AuthService,
              private route: ActivatedRoute,
              public dialogService: DialogService,
              private processoService: ProcessoService) { }

  ngOnInit() {
    this.getProcesso();
    console.log(this.auth.advogado);
  }

  getProcesso() {
    const id = this.route.snapshot.params.id;
    this.processoService.getProcesso(id).subscribe(
      resp => {
        this.processo = resp;
        this.getUsuarios();
      }
    );
  }

  getUsuarios() {
    this.processoService.getUsuarios(this.processo.id).subscribe(
      resp => {
        this.usuarios = resp;
      }
    );
  }

  showUsuarioModal() {
    const ref = this.dialogService.open(AddUsuarioComponent, {
      header: 'Convidar Usuário',
      width: '400px'
    });

    ref.onClose.subscribe((resp) => {
      this.getProcesso();
    });
  }

  showDadosBasicosModal() {
    const ref = this.dialogService.open(CadastroProcessoComponent, {
      header: 'Editar Dados Básicos',
      width: '400px'
    });

    ref.onClose.subscribe(() => {
      this.getProcesso();
    });
  }

  removerUsuarios() {
    this.usuariosSelecionados.forEach(
      id => this.processoService.removeUserInProcesso(this.route.snapshot.params.id, id).subscribe(
        resp => {
          this.getUsuarios();
          alert('Usuario removido com sucesso');
          this.usuariosSelecionados.splice(this.usuariosSelecionados.indexOf(id), 1);
        }
      )
    );
  }

}
