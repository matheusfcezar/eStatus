import { Component, OnInit } from '@angular/core';
import { ProcessoService } from '../processo.service';
import { Usuario } from 'src/app/model';
import { ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/usuario/usuario.service';
import { DynamicDialogRef } from 'primeng/api';

@Component({
  selector: 'app-add-usuario',
  templateUrl: './add-usuario.component.html',
  styleUrls: ['./add-usuario.component.css']
})
export class AddUsuarioComponent implements OnInit {

  advogados = [];
  usuario = new Usuario();
  carregando = false;

  constructor(private processoService: ProcessoService,
              private usuarioService: UsuarioService,
              private route: ActivatedRoute,
              public ref: DynamicDialogRef) { }

  ngOnInit() {
  }

  findAdvogados(busca) {
    this.usuarioService.findAdvogados(busca.query.toUpperCase()).subscribe(
      resp => {
        this.advogados = resp;
      }
    );
  }

  addUser() {
    this.carregando = true;
    this.processoService.addUserInProcesso(this.route.snapshot.params.id, this.usuario).subscribe(
      resp => {
        this.ref.close();
      }
    );
  }

  addAdvogado() {
    this.carregando = true;
  }

}
