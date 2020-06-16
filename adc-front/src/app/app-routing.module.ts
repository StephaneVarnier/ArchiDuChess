import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { GamesComponent} from './games/games.component';
import { OptionsComponent } from './options/options.component';


const routes: Routes = [

  { path: 'games', component: GamesComponent },
  { path: 'options', component: OptionsComponent },
  { path : '', redirectTo:'/games', pathMatch: 'full'},
 
];

@NgModule({

  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
