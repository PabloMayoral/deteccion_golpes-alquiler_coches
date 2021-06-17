import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { Alquiler } from '../_services/alquiler.service';
import swal from 'sweetalert2';
import { Rent } from '../rent-admin/rent'
import { Clientes } from '../clientes-admin/clientes'
import { AuthService } from '../_services/auth.service';
@Component({
  selector: 'app-rent',
  templateUrl: './rent.component.html',
  styleUrls: ['./rent.component.css']
})

export class RentComponent implements OnInit {
  rent: Rent = new Rent();
  private user: Clientes = new Clientes();
  randomChars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  result = '';
  userDNI = null;
  currentUser: any;
  errorMessage = '';
  isSuccessful = false;
  isAddRentFailed = false;
  isLoggedIn = false;
  check = false;
  constructor(private token: TokenStorageService, private alquilerService: Alquiler, private authService: AuthService) { }
  onSubmit() {
    if (!this.check) {
      this.rent.ciudadAlquilerDevolucion = this.rent.ciudadAlquiler;
    }
    for (var i = 0; i < 10; i++) {
      this.result += this.randomChars.charAt(Math.floor(Math.random() * this.randomChars.length));
    }
    this.rent.codRefrencia = this.result
    this.rent.email
    this.alquilerService.registraAlquiler(this.rent).subscribe(
      data => {
        this.alquilerService.sendEmail();
        swal.fire('Alquiler', 'Alquiler realizado correctamente', 'success').then((result) => {
          if (result.value) {
            window.location.reload()
          }
        })
        this.isSuccessful = true;
        this.isAddRentFailed = false;
      },
      err => {
        swal.fire('Alquiler', 'Alquiler no se pudo realizar por favor intentelo de nuevo', 'error')
        this.errorMessage = err.error.message;
        this.isAddRentFailed = true;
      }
    );
  }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.isLoggedIn = !!this.token.getToken();
    this.userDNI = this.currentUser.dni;
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
    this.authService.getCliente(this.currentUser.id).subscribe(user => {
      this.user = user
      this.rent.email = this.user.email
      this.rent.id_cliente = this.user.dni
    })
  }


}
