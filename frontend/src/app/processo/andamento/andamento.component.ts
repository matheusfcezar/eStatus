import { Component, OnInit, Inject, InjectionToken } from '@angular/core';
import { DynamicDialogRef, DynamicDialogConfig, SelectItem } from 'primeng/api';
import { Andamento } from 'src/app/model';
import { ProcessoService } from '../processo.service';

@Component({
  selector: 'app-andamento',
  templateUrl: './andamento.component.html',
  styleUrls: ['./andamento.component.css']
})
export class AndamentoComponent implements OnInit {

  andamento: Andamento;
  tipos: SelectItem[] = [];
  edit: boolean;

  constructor(public ref: DynamicDialogRef,
              public config: DynamicDialogConfig,
              private processoService: ProcessoService) { }

  ngOnInit() {
      if (this.config.data.andamento) {
        this.andamento = this.config.data.andamento;
        this.edit = this.config.data.edit;
      } else {
        this.andamento = new Andamento();
        this.andamento.idProcesso = this.config.data.idProcesso;
      }

      this.tipos = [
        { label: 'Distribuição', value: 'DISTRIBUICAO'},
        { label: 'Atualização Automática', value: 'AUTOMATICO'},
        { label: 'Intimação', value: 'INTIMACAO'},
        { label: 'Decisão', value: 'DECISAO'},
        { label: 'Medida Liminar', value: 'LIMINAR'},
        { label: 'Concluso para decisão', value: 'CONCLUSO'},
        { label: 'Processo remetido', value: 'REMETIDO'},
      ];
  }

  salvar() {
    if (!this.andamento.id) {
      let month = String(this.andamento.data.getMonth() + 1);
      let day = String(this.andamento.data.getDate());
      const year = String(this.andamento.data.getFullYear());

      if (month.length < 2) { month = '0' + month; }
      if (day.length < 2) { day = '0' + day; }
      const a = `${day}/${month}/${year}`;
      this.andamento.data = a;
      console.log(this.andamento);
      // const data = `${this.andamento.data.substr(8, 10)}/${this.andamento.data.substr(7, 8)}/${this.andamento.data.substr(0, 4)}`;
      // console.log(data);
    }
    this.processoService.addAndamento(this.andamento).subscribe(
      resp => {
        this.ref.close();
      }, error => {
        console.log(error);
      }
    );
  }

}
