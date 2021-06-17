import { Component, OnInit } from '@angular/core';
import { Rent } from '../rent-admin/rent'
import { Alquiler } from '../_services/alquiler.service';
import { TokenStorageService } from '../_services/token-storage.service';
import { Route, ActivatedRoute } from '@angular/router';
import { Vehiculos } from '../_services/vehiculos.service';
import { Vehiculo } from '../vehiculos/vehiculos';
import swal from 'sweetalert2';
@Component({
  selector: 'app-asignar-coche',
  templateUrl: './asignar-coche.component.html',
})
export class AsignarCocheComponent implements OnInit {
  vehiculos: Vehiculo[];
  flag: Boolean=true;
  private oldVehiculo: Vehiculo = new Vehiculo();
  public rents: Rent = new Rent();
  oldCarId
  rentId
  isLoggedIn = false;
  currentUser: any;
  constructor(private alquilerService: Alquiler, private tokenStorage: TokenStorageService, private activatedRoute: ActivatedRoute, private vehiculosService: Vehiculos) { }

  flag_(): void {
    this.flag=false;
    console.log('wefuigewuf')
  }

  asignarCocheAlquiler(): void {
    this.activatedRoute.params.subscribe(params => {
      this.rentId = params["id_alquiler"];
      this.oldCarId = params['id_coche']
    })
  }
  ngOnInit(): void {
    this.vehiculosService.getVehiculo().subscribe(vehiculos => { this.vehiculos = vehiculos })
    this.asignarCocheAlquiler()
    this.currentUser = this.tokenStorage.getUser();
    this.isLoggedIn = !!this.tokenStorage.getToken();
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
    this.alquilerService.getUnAlquiler(this.rentId).subscribe(rentes => { this.rents = rentes; })
  }

  asignCar(vehiculo:Vehiculo): void {

    this.rents.id_coche = vehiculo.id
    this.rents.matricula_coche = vehiculo.matricula
    if (this.oldCarId > 0) this.vehiculosService.getUnVehiculo(this.oldCarId).subscribe(oldVehiculo => this.oldVehiculo = oldVehiculo)
    this.alquilerService.updateAlquiler(this.rents, this.rentId).subscribe(
      data => {
        this.vehiculosService.updateNewRent(vehiculo).subscribe(
          data => {
            if (this.oldCarId > 0) this.vehiculosService.updateOldRent(this.oldVehiculo).subscribe();
            swal.fire('Coche asignado', 'Se ha asignado el alquiler', 'success').then((result) => {
              if (result.value) {
                window.location.href = '/rent-admin'
              }
            })
          });
      },
      err => {
        swal.fire('Editar Perfil', err, 'success').then((result) => {
          if (result.value) {
            window.location.href = '/rent-admin'
          }
        })
      }
    );
  }

}
