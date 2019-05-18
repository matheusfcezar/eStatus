import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
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

    findAdvogados(busca: string): Observable<any> {
        return this.httpClient.get(`${this.usuarioUrl}/advogado`, {
            params: new HttpParams().set('busca', busca) });
    }

    findUsuarioPorEmail(busca: string): Observable<any> {
        return this.httpClient.get(`${this.usuarioUrl}/filtrar`, {
            params: new HttpParams().set('email', busca) });
    }

    esqueciMinhaSenha(email: string): Observable<any> {
        return this.httpClient.get(`${this.usuarioUrl}/esqueci-senha?email=${email}`);
    }
}
