import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../model';
import { environment } from 'src/environments/environment';

@Injectable()
export class UsuarioService {

    usuarioUrl = `${environment.apiUrl}/usuario`;
    publicUrl = `${environment.apiUrl}/public`;

    constructor(private httpClient: HttpClient) {}

    cadastrar(usuario: Usuario): Observable<any> {
        return this.httpClient.post(`${this.publicUrl}/usuario/new`, usuario);
    }
}
