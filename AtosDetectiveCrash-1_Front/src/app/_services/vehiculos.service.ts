import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Vehiculo } from '../vehiculos/vehiculos'
const AUTH_API = 'http://localhost:8080/';



@Injectable({
  providedIn: 'root'
})
export class Vehiculos {
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private http: HttpClient) { }

  registraVehiculos(vehiculo:Vehiculo): Observable<any> {
    console.log(vehiculo)
    return this.http.post(AUTH_API + 'vehiculos', vehiculo , { headers: this.httpHeaders });
  }
  getVehiculo(): Observable<Vehiculo[]> {
    return this.http.get<Vehiculo[]>(AUTH_API + 'vehiculos').pipe(
      map((response) => response as Vehiculo[]));
  }

  updateNewRent(vehiculo:Vehiculo): Observable<any> {
    vehiculo['alquilado']='1'
    console.log(vehiculo)
    return this.http.put(AUTH_API + 'vehiculos/' + vehiculo.id , vehiculo, { headers: this.httpHeaders });
  }
  updateOldRent(vehiculo:Vehiculo): Observable<any> {
    vehiculo['alquilado']='0'
    console.log(vehiculo)
    return this.http.put(AUTH_API + 'vehiculos/' + vehiculo.id , vehiculo, { headers: this.httpHeaders });
  }

  getUnVehiculo(id: Number): Observable<Vehiculo> {
    return this.http.get<Vehiculo>(AUTH_API + 'vehiculos/' + id);
  }
}
