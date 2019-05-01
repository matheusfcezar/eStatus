import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProcessoRoutingModule } from './processo-routing.module';
import { CadastroProcessoComponent } from './cadastro-processo/cadastro-processo.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ProcessoService } from './processo.service';
import { DropdownModule } from 'primeng/dropdown';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { EditProcessoComponent } from './edit-processo/edit-processo.component';
import { PanelModule } from 'primeng/panel';
import { TableModule } from 'primeng/table';
import { AddUsuarioComponent } from './add-usuario/add-usuario.component';
import { TabViewModule } from 'primeng/tabview';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { CheckboxModule } from 'primeng/checkbox';

@NgModule({
  declarations: [CadastroProcessoComponent, EditProcessoComponent, AddUsuarioComponent],
  imports: [
    CommonModule,
    ProcessoRoutingModule,
    FormsModule,
    ReactiveFormsModule,

    ButtonModule,
    InputTextModule,
    InputTextareaModule,
    DropdownModule,
    AutoCompleteModule,
    PanelModule,
    TableModule,
    TabViewModule,
    ProgressSpinnerModule,
    CheckboxModule
  ],
  providers: [ProcessoService],
  entryComponents: [CadastroProcessoComponent, AddUsuarioComponent]
})
export class ProcessoModule { }
