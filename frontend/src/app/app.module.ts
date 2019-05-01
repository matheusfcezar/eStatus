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
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { MenubarModule } from 'primeng/menubar';
import { InputTextModule } from 'primeng/inputtext';
import { FormsModule } from '@angular/forms';
import { DynamicDialogModule } from 'primeng/dynamicdialog';
import { CadastroProcessoComponent } from './processo/cadastro-processo/cadastro-processo.component';
import { ProcessoService } from './processo/processo.service';
import { DropdownModule } from 'primeng/dropdown';
import { AddUsuarioComponent } from './processo/add-usuario/add-usuario.component';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    IndexComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    RouterModule,
    UsuarioModule,
    HttpClientModule,
    FormsModule,

    ButtonModule,
    SplitButtonModule,
    MenuModule,
    TableModule,
    MenubarModule,
    InputTextModule,
    DynamicDialogModule,
    DropdownModule,

    AppRoutingModule,
    SegurancaModule
  ],
  providers: [AppService, ProcessoService, { provide: HTTP_INTERCEPTORS, useClass: JwtHttpInterceptor, multi: true }],
  bootstrap: [AppComponent],
  entryComponents: [CadastroProcessoComponent, AddUsuarioComponent]
})
export class AppModule { }
