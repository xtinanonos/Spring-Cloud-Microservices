// archivo donde se especifica como se deben manejar las URLs y a que componentes hacen referencia

import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { AuthorListComponent } from './author/author-list/author-list.component';
import { GameListComponent } from './game/game-list/game-list.component';
import { ClientListComponent } from './client/client-list/client-list.component';
import { LoanListComponent } from './loan/loan-list/loan-list.component';

const routes: Routes = [
   // si no se pone ruta, la pagina inicial redirige a la pantalla de juegos
  { path: '', redirectTo: '/games', pathMatch: 'full'},    
  { path: 'categories', component: CategoryListComponent },
  { path: 'authors', component: AuthorListComponent },
  { path: 'games', component: GameListComponent },
  { path: 'clients', component: ClientListComponent },
  { path: 'loans', component: LoanListComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
