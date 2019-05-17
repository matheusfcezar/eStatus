import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Processo, Usuario, Andamento } from 'src/app/model';
import { ProcessoService } from '../processo.service';
import { AuthService } from 'src/app/seguranca/auth.service';
import { DialogService, ConfirmationService } from 'primeng/api';
import { AddUsuarioComponent } from '../add-usuario/add-usuario.component';
import { CadastroProcessoComponent } from '../cadastro-processo/cadastro-processo.component';
import { AndamentoComponent } from '../andamento/andamento.component';

@Component({
  selector: 'app-edit-processo',
  templateUrl: './edit-processo.component.html',
  styleUrls: ['./edit-processo.component.css'],
  providers: [DialogService]
})
export class EditProcessoComponent implements OnInit {

  usuariosSelecionados = [];
  andamentoSelecionado: Andamento;
  andamentos: Andamento[] = [];
  processo: Processo;
  usuarios: Usuario[];

  constructor(public auth: AuthService,
              private route: ActivatedRoute,
              public dialogService: DialogService,
              private processoService: ProcessoService,
              private confirmationService: ConfirmationService) { }

  ngOnInit() {
    this.getProcesso();
  }

  getProcesso() {
    const id = this.route.snapshot.params.id;
    this.processoService.getProcesso(id).subscribe(
      resp => {
        this.processo = resp;
        this.getUsuarios();
        this.getAndamentos();
      }
    );
  }

  getAndamentos() {
    this.processoService.getAndamentos(this.processo.id).subscribe(
      resp => {
        this.andamentos = resp;
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

  showAndamentoModal(andamento) {
    console.log(andamento);
    const ref = this.dialogService.open(AndamentoComponent, {
      width: '400px',
      data: { andamento: andamento ? andamento : this.andamentoSelecionado, idProcesso: this.processo.id, edit: andamento ? true : false },
      closeOnEscape: true,
      dismissableMask: true,
      showHeader: false
    });

    ref.onClose.subscribe((resp) => {
      this.andamentoSelecionado = null;
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

  removerAndamento(id) {
    this.processoService.removeAndamento(id).subscribe(
      resp => {
        this.getProcesso();
      }
    );
  }

  confirmarExclusaoDeAndamento(id) {
    this.confirmationService.confirm({
      message: 'Você confirma a exclusão deste Expediente?',
      accept: () => {
          this.removerAndamento(id);
      }
  });
  }

}
