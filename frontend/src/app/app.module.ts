import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SegurancaModule } from './seguranca/seguranca.module';
import { RouterModule } from '@angular/router';
import { UsuarioModule } from './usuario/usuario.module';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { SplitButtonModule } from 'primeng/splitbutton';
import { JwtHttpInterceptor } from './jwt.httpinterceptor';
import { IndexComponent } from './index/index.component';
import { AppService } from './app.service';
import { ButtonModule } from 'primeng/button';
import {MenuModule} from 'primeng/menu';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    UsuarioModule,
    HttpClientModule,

    ButtonModule,
    SplitButtonModule,
    MenuModule,
    
    AppRoutingModule,
    SegurancaModule
  ],
  providers: [AppService, { provide: HTTP_INTERCEPTORS, useClass: JwtHttpInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
