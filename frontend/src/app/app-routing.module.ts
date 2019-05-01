import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './seguranca/login/login.component';
import { IndexComponent } from './index/index.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'index', component: IndexComponent},
  { path: 'usuario', loadChildren: './usuario/usuario-routing.module#UsuarioRoutingModule' },
  { path: 'processo', loadChildren: './processo/processo-routing.module#ProcessoRoutingModule' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
