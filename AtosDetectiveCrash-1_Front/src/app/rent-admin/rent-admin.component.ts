import { Component, OnInit } from '@angular/core';
import { Rent } from '../rent-admin/rent'
import { Alquiler } from '../_services/alquiler.service';
import { Vehiculos } from '../_services/vehiculos.service';
import { Vehiculo } from '../vehiculos/vehiculos';
import swal from 'sweetalert2';
@Component({
  selector: 'app-rent-admin',
  templateUrl: './rent-admin.component.html',
  styleUrls: ['./rent-admin.component.css']
})
export class RentAdminComponent implements OnInit {
  rents: Rent[];
  private oldVehiculo: Vehiculo = new Vehiculo();
  constructor(private alquilerService: Alquiler, private vehiculosService: Vehiculos) { }

  ngOnInit(): void {
    this.alquilerService.getAlquiler().subscribe(rents => { this.rents = rents }
    );
  }

  unsignCar(rent: Rent): void {
    swal.fire({
      title: '¿Quieres desasignar esta reserva?',
      showCancelButton: true,
      confirmButtonText: `Desasignar`,
      cancelButtonText: `Cancelar`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.vehiculosService.getUnVehiculo(rent.id_coche).subscribe(oldVehiculo => { this.oldVehiculo = oldVehiculo });
        rent.id_coche = null
        rent.matricula_coche = null
        this.alquilerService.updateAlquiler(rent, rent.id_alquiler).subscribe(
          data => {
            this.vehiculosService.updateOldRent(this.oldVehiculo).subscribe();
            swal.fire('El coche se desasignó correctamente', '', 'success')
          }, err => {

          });
      } else if (result.isDenied) {
        swal.fire('No se desasigno ningun coche', '', 'info')
      }
    })

  }

}
