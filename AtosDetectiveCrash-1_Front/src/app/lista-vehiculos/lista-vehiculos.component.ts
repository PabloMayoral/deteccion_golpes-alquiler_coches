import { Component, OnInit } from '@angular/core';
import { Vehiculo } from '../vehiculos/vehiculos';
import { Vehiculos } from '../_services/vehiculos.service';
@Component({
  selector: 'app-lista-vehiculos',
  templateUrl: './lista-vehiculos.component.html',
  styleUrls: ['./lista-vehiculos.component.css']
})
export class ListaVehiculosComponent implements OnInit {
  vehiculos: Vehiculo[];
  constructor(private vehiculosService: Vehiculos) { }

  ngOnInit(): void {
     this.vehiculosService.getVehiculo().subscribe(vehiculos => { this.vehiculos = vehiculos})
  }

}
