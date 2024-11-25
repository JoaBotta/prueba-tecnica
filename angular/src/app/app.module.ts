import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { PersonalDetailsComponent } from './components/personal-details/personal-details.component';
import { SensoresInactivosComponent } from './components/sensores-inactivos/sensores-inactivos.component';
import { RegisterComponent } from './auth/register/register.component';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { PlantaComponent } from './components/planta/planta.component';
import { FormsModule } from '@angular/forms';
import { BodyComponent } from './body/body.component';
/*import { SidenavComponent } from './sidenav/sidenav.component';*/
import { SidenavComponent } from './sistema/sidenav/sidenav.component';
import { SistemaComponent } from './sistema/sistema.component';
import { MenuComponent } from './shared/menu/menu.component';
import { MonitoreoPorPlantaComponent } from './components/monitoreo-por-planta/monitoreo-por-planta.component';
import { HistoricosPlantasComponent } from './components/historicos-sensores/historicos-plantas.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PersonalDetailsComponent,
    RegisterComponent,
    SensoresInactivosComponent,
    PlantaComponent,
    BodyComponent,
    SidenavComponent,
    SistemaComponent,
    MenuComponent,
    MonitoreoPorPlantaComponent,
    HistoricosPlantasComponent
    
  ],
  imports: [
    BrowserModule,
    FormsModule,
    MatMenuModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatListModule,
    MatSidenavModule,
    MatToolbarModule,
    MatCardModule,
    MatGridListModule,
    MatMenuModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatCheckboxModule
  ],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
