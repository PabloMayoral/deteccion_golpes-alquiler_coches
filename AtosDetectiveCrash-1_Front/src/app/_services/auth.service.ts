import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';
import { User } from '../register/user'
import { map } from 'rxjs/operators';
import { Clientes } from '../clientes-admin/clientes'

const AUTH_API = 'http://localhost:8080/';


@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnInit {
  currentUser: User;
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })

  constructor(private http: HttpClient, private token: TokenStorageService) { }

  login(username: string, password: string): Observable<any> {
    return this.http.post(AUTH_API + 'api/auth/signin', {
      username,
      password
    }, { headers: this.httpHeaders });
  }
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }
  register(user: User): Observable<User> {
    return this.http.post<User>(AUTH_API + 'api/auth/signup', user, { headers: this.httpHeaders });
  }

  editaPerfil(cliente: Clientes, id: string): Observable<any> {
    return this.http.put(AUTH_API + 'usuarios/' + id, cliente, { headers: this.httpHeaders });
  }

  getClientes(): Observable<Clientes[]> {
    return this.http.get<Clientes[]>(AUTH_API + 'usuarios').pipe(
      map((response) => response as Clientes[]));
  }

  getCliente(id: string): Observable<Clientes> {
    return this.http.get<Clientes>(AUTH_API + 'usuarios/' + id);
  }
}
