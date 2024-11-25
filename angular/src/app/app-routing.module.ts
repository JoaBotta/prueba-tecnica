import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { PersonalDetailsComponent } from './components/personal-details/personal-details.component';
import { AuthGuard } from '../app/core/services/auth.guard';
import { PlantaComponent } from './components/planta/planta.component';
import { SistemaComponent } from './sistema/sistema.component';
import { MonitoreoPorPlantaComponent } from './components/monitoreo-por-planta/monitoreo-por-planta.component';
import { SensoresInactivosComponent } from './components/sensores-inactivos/sensores-inactivos.component';
import { HistoricosPlantasComponent } from './components/historicos-sensores/historicos-plantas.component';



const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: '', 
    component: SistemaComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'planta', component: PlantaComponent },
      {path: 'monitoreo-por-planta', component: MonitoreoPorPlantaComponent},
      { path: 'historicos-sensores', component: SensoresInactivosComponent },
      { path: 'historico-planta', component: HistoricosPlantasComponent },
      { path: 'personal-details', component: PersonalDetailsComponent },

      { path: '', redirectTo: 'planta', pathMatch: 'full' },
    ],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


