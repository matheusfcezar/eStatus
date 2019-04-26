import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class AppService {
    apiUrl = environment.apiUrl;

    constructor(private http: HttpClient) {}

    getOk(): Observable<any> {
        return this.http.get(`${this.apiUrl}/usuario/ok`);
    }
}
