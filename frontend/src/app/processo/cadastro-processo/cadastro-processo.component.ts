import { Component, OnInit } from '@angular/core';
import { ProcessoService } from '../processo.service';
import { Processo } from 'src/app/model';
import { SelectItem, DynamicDialogRef } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-cadastro-processo',
  templateUrl: './cadastro-processo.component.html',
  styleUrls: ['./cadastro-processo.component.css']
})
export class CadastroProcessoComponent implements OnInit {

  processo = new Processo();
  foros = [];
  naturezas: SelectItem[] = [];
  editando = false;
  carregando = false;

  constructor(private processoService: ProcessoService,
              private router: Router,
              private route: ActivatedRoute,
              public ref: DynamicDialogRef) { }

  ngOnInit() {
    if (this.route.snapshot.params.id) {
      this.editando = true;
    }
    if (this.editando) {
      this.carregando = true;
      this.processoService.getProcesso(this.route.snapshot.params.id).subscribe(
        resp => this.processo = resp
      );
    }
    this.processoService.getNaturezas().subscribe(
      resp => {
        resp.forEach(n => this.naturezas.push({label: n, value: n}));
      }
    );
  }

  findForos(busca) {
    this.processoService.getForos(busca.query.toUpperCase()).subscribe(
      resp => {
        this.foros = resp;
      }
    );
  }

  salvar() {
    if (!this.processo.id) {
      this.processoService.criarProcesso(this.processo).subscribe(
        resp => {
          console.log(resp);
          if (resp.id) {
            this.router.navigate(['processo/' + resp.id]);
          }
        }
      );
    } else {
      this.processoService.editarProcesso(this.processo).subscribe(
        resp => {
          console.log(resp);
          if (resp.id) {
            this.ref.close();
            this.router.navigate(['processo/' + resp.id]);
          }
        }
      );
    }
  }

}
