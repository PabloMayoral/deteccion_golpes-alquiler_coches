import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { TooltipModule } from 'ngx-bootstrap/tooltip';
import { ModalModule } from 'ngx-bootstrap/modal';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { RentComponent } from './rent/rent.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { VehiculosComponent } from './vehiculos/vehiculos.component';
import { CrashDetectorComponent } from './crash-detector/crash-detector.component';
import { EditarPerfilComponent } from './editar-perfil/editar-perfil.component';
import { RentAdminComponent } from './rent-admin/rent-admin.component';
import { ClientesAdminComponent } from './clientes-admin/clientes-admin.component';
import { ComparacionAdminComponent } from './comparacion-admin/comparacion-admin.component';
import { AsignarCocheComponent } from './asignar-coche/asignar-coche.component';
import { ListaVehiculosComponent } from './lista-vehiculos/lista-vehiculos.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    RentComponent,
    VehiculosComponent,
    CrashDetectorComponent,
    EditarPerfilComponent,
    RentAdminComponent,
    ClientesAdminComponent,
    ComparacionAdminComponent,
    AsignarCocheComponent,
    ListaVehiculosComponent,

  ],
  imports: [
    BrowserModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  exports: [BsDropdownModule, TooltipModule, ModalModule],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
