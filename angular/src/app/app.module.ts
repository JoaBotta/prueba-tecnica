import { NgModule } from '@angular/core';
import { JwtInterceptor } from '../app/core/validator/jwt.interceptor';
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

import { MatProgressBarModule } from '@angular/material/progress-bar';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { SensoresInactivosComponent } from './modules/sensores-inactivos/sensores-inactivos.component';
import { RegisterComponent } from './auth/register/register.component';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { PlantaComponent } from './modules/planta/planta.component';
import { FormsModule } from '@angular/forms';
import { BodyComponent } from './body/body.component';
/*import { SidenavComponent } from './sidenav/sidenav.component';*/
import { SidenavComponent } from './sistema/sidenav/sidenav.component';
import { SistemaComponent } from './sistema/sistema.component';
import { MenuComponent } from './shared/menu/menu.component';
import { MonitoreoPorPlantaComponent } from './modules/monitoreo-por-planta/monitoreo-por-planta.component';
import { HistoricosPlantasComponent } from './modules/historicos-sensores/historicos-plantas.component';
import { ProductoListaComponent } from './modules/productos/producto-lista/producto-lista.component';
import { ProductoFormComponent } from './modules/productos/producto-form/producto-form.component';
import { UsuarioDetalleComponent } from './modules/usuario-detalle/usuario-detalle.component';
import { BolicheComponent } from './modules/boliche/boliche.component';
import { GestionBolicheComponent } from './modules/gestion-boliche/gestion-boliche.component';
import { BolicheFormComponent } from './modules/boliche/boliche-form/boliche-form.component';
import { BarrasComponent } from './modules/barras/barras.component';
import { VentasBarraComponent } from './modules/venta/VentaBarra/ventas-barra/ventas-barra.component';
import { CrearVentaBarraComponent } from './modules/venta/VentaBarra/crear-venta-barra/crear-venta-barra.component';
import { TicketComponent } from './modules/venta/ticket/ticket.component';
import { CrearUsuarioComponent } from './auth/crear-usuario/crear-usuario.component';
import { PuntoDeVentaComponent } from './modules/punto-de-venta/punto-de-venta.component';


@NgModule({
  declarations: [    
    AppComponent,
    LoginComponent,
    RegisterComponent,
    SensoresInactivosComponent,
    PlantaComponent,
    BodyComponent,
    SidenavComponent,
    SistemaComponent,
    MenuComponent,
    MonitoreoPorPlantaComponent,
    HistoricosPlantasComponent,
    ProductoListaComponent,
    ProductoFormComponent,
    UsuarioDetalleComponent,
    BolicheComponent,
    GestionBolicheComponent,
    BolicheFormComponent,
    BarrasComponent,
    VentasBarraComponent,
    CrearVentaBarraComponent,
    TicketComponent,
    CrearUsuarioComponent,
    PuntoDeVentaComponent
    
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
    MatCheckboxModule,
    MatProgressBarModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    },
  ],
  
  bootstrap: [AppComponent]
})
export class AppModule { }
