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
import { AndamentoComponent } from './andamento/andamento.component';
import { CalendarModule } from 'primeng/calendar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { AddArquivoComponent } from './add-arquivo/add-arquivo.component';
import { FileUploadModule } from 'primeng/fileupload';
import { OverlayPanelModule } from 'primeng/overlaypanel';

@NgModule({
  declarations: [CadastroProcessoComponent, EditProcessoComponent, AddUsuarioComponent, AndamentoComponent, AddArquivoComponent],
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
    CheckboxModule,
    CalendarModule,
    ConfirmDialogModule,
    FileUploadModule,
    OverlayPanelModule
  ],
  providers: [ProcessoService],
  entryComponents: [AddArquivoComponent, CadastroProcessoComponent, AddUsuarioComponent, AndamentoComponent]
})
export class ProcessoModule { }
