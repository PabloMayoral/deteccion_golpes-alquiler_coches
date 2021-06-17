import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { RentComponent } from './rent/rent.component';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { CrashDetectorComponent } from './crash-detector/crash-detector.component';
import { EditarPerfilComponent } from './editar-perfil/editar-perfil.component';
import { RentAdminComponent } from './rent-admin/rent-admin.component';
import { ClientesAdminComponent } from './clientes-admin/clientes-admin.component';
import { ComparacionAdminComponent } from './comparacion-admin/comparacion-admin.component';
import { AsignarCocheComponent } from './asignar-coche/asignar-coche.component';
import { ListaVehiculosComponent } from './lista-vehiculos/lista-vehiculos.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'rent', component: RentComponent },
  { path: 'vehiculos', component: VehiculosComponent },
  { path: 'detection', component: CrashDetectorComponent },
  { path: 'profile/editar-perfil', component: EditarPerfilComponent },
  { path: 'rent-admin', component: RentAdminComponent },
  { path: 'clientes-admin', component: ClientesAdminComponent },
  { path: 'comparacion-admin', component: ComparacionAdminComponent },
  { path: 'rent-admin/asignar-coche/:id_alquiler/:id_coche', component: AsignarCocheComponent },
  { path: 'lista-vehiculos', component: ListaVehiculosComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
