import { Component, OnInit } from '@angular/core';
import { Processo } from '../model';
import { DialogService } from 'primeng/api';
import { CadastroProcessoComponent } from '../processo/cadastro-processo/cadastro-processo.component';
import { ProcessoService } from '../processo/processo.service';
import { Router } from '@angular/router';
import { AuthService } from '../seguranca/auth.service';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css'],
  providers: [DialogService]
})
export class IndexComponent implements OnInit {

  processos: Processo[] = [];
  processoSelecionado: Processo;
  buscou = false;

  constructor(private processoService: ProcessoService,
              public dialogService: DialogService,
              private router: Router,
              public auth: AuthService) { }

  ngOnInit() {
    this.getProcessos();
  }

  getProcessos() {
    this.processoService.getProcessos().subscribe(
      resp => {
        this.processos = resp;
      }
    );
  }

  filtrarProcessos(busca) {
    this.processoService.filtrarProcessos(busca).subscribe(
      resp => {
        this.processos = resp;
        this.buscou = true;
      }
    );
  }

  limparBusca(event: MouseEvent, input) {
    this.getProcessos();
    event.preventDefault();
    input.value = null;
    this.buscou = false;
  }

  show() {
    const ref = this.dialogService.open(CadastroProcessoComponent, {
      header: 'Novo Processo',
      width: '400px'
    });

    ref.onClose.subscribe(() => {
      console.log('fechou');
    });
  }

  onRowSelect(event) {
    this.router.navigate(['processo/' + this.processoSelecionado.id]);
  }

}
