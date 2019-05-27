import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Processo, Usuario, Andamento, Arquivo } from 'src/app/model';
import { ProcessoService } from '../processo.service';
import { AuthService } from 'src/app/seguranca/auth.service';
import { DialogService, ConfirmationService } from 'primeng/api';
import { AddUsuarioComponent } from '../add-usuario/add-usuario.component';
import { CadastroProcessoComponent } from '../cadastro-processo/cadastro-processo.component';
import { AndamentoComponent } from '../andamento/andamento.component';
import { AddArquivoComponent } from '../add-arquivo/add-arquivo.component';
import { OverlayPanel } from 'primeng/overlaypanel';

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
  docs: Arquivo[];
  arquivoSelecionado: Arquivo;

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
        this.getArquivos();
      }
    );
  }

  getArquivos() {
    this.processoService.getArquivos(this.processo.id).subscribe(
      resp => {
        this.docs = resp;
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
      width: '800px',
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

  showAddArquivoModal() {
    const ref = this.dialogService.open(AddArquivoComponent, {
      header: 'Adicionar arquivos',
      width: '400px',
      data: { id: this.processo.id }
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

  removerDocumento(id) {
    this.processoService.removeArquivo(id).subscribe(
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

  confirmarExclusaoDeArquivo(id) {
    this.confirmationService.confirm({
      message: 'Você confirma a exclusão deste Arquivo?',
      accept: () => {
        this.removerDocumento(id);
      }
    });
  }

  showArquivo(event, arquivo: Arquivo, overlayPanel: OverlayPanel) {
    this.arquivoSelecionado = arquivo;
    overlayPanel.show(event);
  }

  hideArquivo(event, overlayPanel: OverlayPanel) {
    console.log('sasiu');
    overlayPanel.hide();
  }

  getArquivo(doc: Arquivo) {
    let linkSource = '';
    if (this.isImagem(doc)) {
      linkSource = `data:image/${doc.extensao};base64,${doc.dados}`;
    } else {
      linkSource = 'data:application/octet-stream;base64,' + doc.dados;
    }
    const downloadLink = document.createElement('a');

    downloadLink.href = linkSource;
    downloadLink.download = doc.nome;
    downloadLink.click();
  }

  get arquivoSource() {

    return `data:image/${this.arquivoSelecionado.extensao};base64,${this.arquivoSelecionado.dados}`;
  }

  isImagem(extensao): boolean {
    return extensao === 'jpg' || extensao === 'jpeg' || extensao === 'png' || extensao === 'gif';
  }

}
