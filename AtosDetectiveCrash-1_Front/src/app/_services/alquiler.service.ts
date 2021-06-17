import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Rent } from '../rent-admin/rent'

const AUTH_API = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class Alquiler {
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private http: HttpClient) { }

  getAlquiler(): Observable<Rent[]> {
    return this.http.get<Rent[]>(AUTH_API + 'alquiler').pipe(
      map((response) => response as Rent[]));
  }
  updateAlquiler(rent: Rent, id: Number): Observable<any> {
    console.log(rent)
    return this.http.put(AUTH_API + 'alquiler/' + id, rent, { headers: this.httpHeaders })
  }
  registraAlquiler(rent: Rent): Observable<Rent> {
    return this.http.post<Rent>(AUTH_API + 'alquiler', rent, { headers: this.httpHeaders });
  }
  getUnAlquiler(id:Number): Observable<Rent> {
    return this.http.get<Rent>(AUTH_API + 'alquiler/' + id);
  }

  getUnAlquilerCodRef(cod_ref): Observable<Rent> {
    return this.http.get<Rent>(AUTH_API + 'alquileres/' + cod_ref, { headers: this.httpHeaders });
  }

  sendEmail(): Observable<any> {
    return this.http.post(AUTH_API + 'alquileres/sendEmail', { headers: this.httpHeaders });
  }
}
