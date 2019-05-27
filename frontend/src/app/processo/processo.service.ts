import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Processo, Usuario, Andamento } from '../model';

@Injectable()
export class ProcessoService {
    processoUrl = `${environment.apiUrl}/processo`;
    foroUrl = `${environment.apiUrl}/foro`;
    andamentoUrl = `${environment.apiUrl}/andamento`;
    arquivoUrl = `${environment.apiUrl}/arquivo`;

    constructor(private http: HttpClient) {}

    getNaturezas(): Observable<any> {
        return this.http.get(`${this.processoUrl}/naturezas`);
    }

    getForos(busca: string): Observable<any> {
        return this.http.get(`${this.foroUrl}/filtrar`, {
            params: new HttpParams().set('busca', busca) });
    }

    criarProcesso(processo: Processo): Observable<any> {
        return this.http.post(`${this.processoUrl}/new`, processo);
    }

    editarProcesso(processo: Processo): Observable<any> {
        return this.http.put(`${this.processoUrl}/${processo.id}`, processo);
    }

    getProcessos(): Observable<any> {
        return this.http.get(this.processoUrl);
    }

    filtrarProcessos(busca: string): Observable<any> {
        return this.http.get(`${this.processoUrl}/search`, {
            params: new HttpParams().set('busca', busca) });
    }

    getProcesso(id: number): Observable<any> {
        return this.http.get(`${this.processoUrl}/${id}`);
    }

    getUsuarios(id: number): Observable<any> {
        return this.http.get(`${this.processoUrl}/${id}/users`);
    }

    getAndamentos(id: number): Observable<any> {
        return this.http.get(`${this.andamentoUrl}/processo/${id}`);
    }

    getArquivos(id: number): Observable<any> {
        return this.http.get(`${this.arquivoUrl}/processo/${id}`);
    }

    removeArquivo(id: number): Observable<any> {
        return this.http.delete(`${this.arquivoUrl}/${id}`);
    }

    addAndamento(andamento: Andamento): Observable<any> {
        return this.http.post(`${this.andamentoUrl}`, andamento);
    }

    removeAndamento(id: number): Observable<any> {
        return this.http.delete(`${this.andamentoUrl}/${id}`);
    }

    addUserInProcesso(id: number, usuario: Usuario): Observable<any> {
        return this.http.post(`${this.processoUrl}/${id}/users`, usuario);
    }

    removeUserInProcesso(idProcesso: number, idUsuario): Observable<any> {
        return this.http.delete(`${this.processoUrl}/${idProcesso}/${idUsuario}`);
    }

}
