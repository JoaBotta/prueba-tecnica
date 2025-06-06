import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { AuthGuard } from '../app/core/services/auth.guard';
import { PlantaComponent } from './modules/planta/planta.component';
import { SistemaComponent } from './sistema/sistema.component';
import { MonitoreoPorPlantaComponent } from './modules/monitoreo-por-planta/monitoreo-por-planta.component';
import { SensoresInactivosComponent } from './modules/sensores-inactivos/sensores-inactivos.component';
import { HistoricosPlantasComponent } from './modules/historicos-sensores/historicos-plantas.component';
import { ProductoListaComponent } from './modules/productos/producto-lista/producto-lista.component';
import {ProductoFormComponent} from './modules/productos/producto-form/producto-form.component';
import {UsuarioDetalleComponent} from './modules/usuario-detalle/usuario-detalle.component';
import { BolicheComponent } from './modules/boliche/boliche.component';
import { GestionBolicheComponent } from './modules/gestion-boliche/gestion-boliche.component';
import { BolicheFormComponent } from './modules/boliche/boliche-form/boliche-form.component';
import { BarrasComponent } from './modules/barras/barras.component';
import { VentasBarraComponent } from './modules/venta/VentaBarra/ventas-barra/ventas-barra.component';
import { CrearVentaBarraComponent } from './modules/venta/VentaBarra/crear-venta-barra/crear-venta-barra.component';
import { CrearUsuarioComponent } from './auth/crear-usuario/crear-usuario.component';
import { PuntoDeVentaComponent } from './modules/punto-de-venta/punto-de-venta.component'; 
import { EntradaListComponent } from './modules/entrada/entrada-list/entrada-list.component';
import { EntradaFormComponent } from './modules/entrada/entrada-form/entrada-form.component';
import { EntradaOnlineListComponent } from './modules/entrada/entrada-online-list/entrada-online-list.component';
import { EntradaOnlineFormComponent } from './modules/entrada/entrada-online-form/entrada-online-form.component';
import { VentaEntradaOnlineComponent } from './modules/venta-entrada/venta-entrada-online/venta-entrada-online.component'; // Importa el componente de ventas de entradas online
import { ClienteListComponent } from './modules/clientes/cliente-list/cliente-list.component';
import { ClienteFormComponent } from './modules/clientes/cliente-form/cliente-form.component';
import { HistorialComponent } from './modules/historial/historial/historial.component';
import { ListaListComponent } from './modules/lista/lista-list/lista-list.component';
import { ListaFormComponent } from './modules/lista/lista-form/lista-form.component';


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
      { path: 'perfil', component: UsuarioDetalleComponent },
       // Rutas de productos
      { path: 'productos', component: ProductoListaComponent },
      { path: 'productos/agregar', component: ProductoFormComponent },
      { path: 'productos/editar/:id', component: ProductoFormComponent },
      {path: 'boliches', component: BolicheComponent}, 
      { path: 'boliche/:id/gestion', component: GestionBolicheComponent },
      {path: 'boliche/agregar', component: BolicheFormComponent},
      { path: 'boliche/:id/barras', component: BarrasComponent },
      { path: 'boliche/:bolicheId/barras/:barraId/ventas-barra', component: VentasBarraComponent },
      { path: 'boliche/:bolicheId/barras/:barraId/ventas-barra/crear', component: CrearVentaBarraComponent },
      {path: 'boliche/:bolicheId/crear-usuario', component: CrearUsuarioComponent},
      {path: 'boliche/:bolicheId/puntos-de-venta', component: PuntoDeVentaComponent},
      {path: 'entrada/list', component: EntradaListComponent},
      {path: 'entrada/form/:id', component: EntradaFormComponent},
      {path: 'entrada/form', component: EntradaFormComponent},
      {path: 'entrada-online/list', component: EntradaOnlineListComponent},
      {path: 'entrada-online/form/:id', component: EntradaOnlineFormComponent},
      {path: 'entrada-online/form', component: EntradaOnlineFormComponent},
      {path: 'venta-entrada-online', component: VentaEntradaOnlineComponent}, // Ruta para el componente de ventas de entradas online
      { path: 'listas', component: ListaListComponent },
      { path: 'listas/nueva', component: ListaFormComponent },
      { path: 'listas/:id', component: ClienteListComponent },         // Ver clientes de una lista
      { path: 'listas/:id/agregar-cliente', component: ClienteFormComponent },
      { path: 'historial', component: HistorialComponent },


      { path: '', redirectTo: 'boliches', pathMatch: 'full' },
    ],
  },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }


