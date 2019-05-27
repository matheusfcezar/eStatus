import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { environment } from '../environments/environment';

@Injectable()
export class JwtHttpInterceptor implements HttpInterceptor {

  constructor() { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');
    let clone: HttpRequest<any>;
    if (request.url.toString().includes('arquivo/add')) {
      return next.handle(request.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      }));
    }
    if (token &&
      (!request.url.toString().startsWith(`${environment.apiUrl}/oauth`)
      && !request.url.toString().startsWith(`${environment.apiUrl}/public`))) {
      clone = request.clone({
        setHeaders: {
          Accept: `application/json`,
          'Content-Type': `application/json`,
          Authorization: `Bearer ${token}`
        }
      });
    } else {
      if (!request.url.toString().startsWith(`${environment.apiUrl}/oauth`)
      && !request.url.toString().includes('ativar')) {
        clone = request.clone({
          setHeaders: {
            Accept: `application/json`,
            'Content-Type': `application/json`
          }
        });
      } else {
        clone = request.clone();
      }
    }
    return next.handle(clone);
  }
}
