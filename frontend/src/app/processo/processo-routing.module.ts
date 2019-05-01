import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EditProcessoComponent } from './edit-processo/edit-processo.component';

const routes: Routes = [
  { path: ':id', component: EditProcessoComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProcessoRoutingModule { }
