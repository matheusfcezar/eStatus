import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { DomSanitizer } from '@angular/platform-browser';
import { AuthService } from 'src/app/seguranca/auth.service';
import { ProcessoService } from '../processo.service';
import { DynamicDialogRef, DynamicDialogConfig } from 'primeng/api';

@Component({
  selector: 'app-add-arquivo',
  templateUrl: './add-arquivo.component.html',
  styleUrls: ['./add-arquivo.component.css']
})
export class AddArquivoComponent implements OnInit {

  idProcesso: number;
  controllerSrc: any;
  uploadEmAndamento = false;

  constructor(private sanitizer: DomSanitizer,
              private auth: AuthService,
              private processoService: ProcessoService,
              public ref: DynamicDialogRef,
              public config: DynamicDialogConfig) { }

  ngOnInit() {
    this.idProcesso = this.config.data.id;
  }

  get urlUpload() {
    return `${environment.apiUrl}/processo/arquivo/add/${this.idProcesso}`;
  }

  getSafeUrl(nome: string) {
    const url = `${environment.apiUrl}/processo`;
    this.controllerSrc = this.sanitizer.bypassSecurityTrustResourceUrl(url);
    return this.controllerSrc;
  }

  preUpload(event) {
    this.uploadEmAndamento = true;

    if (this.auth.isAccessTokenInvalido()) {
      console.log('Requisição HTTP com access token inválido. Obtendo novo token...');
      this.auth.obterNovoAccessToken()
        .then(response => console.log(response));
    }
    console.log(event);
    // event.xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
  }

  posUpload(event) {
    this.uploadEmAndamento = false;
    if (event.originalEvent.status === 200) {
      this.ref.close();
    }
  }

  errorUpload(event) {
    alert('Erro ao enviar arquivo');
    this.uploadEmAndamento = false;
  }

}
