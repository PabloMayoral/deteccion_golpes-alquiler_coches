import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';
import swal from 'sweetalert2';
import { Clientes } from '../clientes-admin/clientes'
@Component({
  selector: 'app-editar-perfil',
  templateUrl: './editar-perfil.component.html',
  //styleUrls: ['./editar-perfil.component.css']
})
export class EditarPerfilComponent implements OnInit {
  private user: Clientes = new Clientes();
  date: Date
  currentUser: any;
  isLoggedIn = false;
  userId = ''
  errorMessage = '';
  isSuccessful = false;
  constructor(private authService: AuthService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.currentUser = this.tokenStorage.getUser();
    this.userId = this.currentUser.id;
    this.date = this.currentUser.date
    this.isLoggedIn = !!this.tokenStorage.getToken();
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
    this.authService.getCliente(this.userId).subscribe(user => this.user = user)
  }

  onSubmit(): void {
    swal.fire({
      title: '¿Quieres editar tu perfil?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: `Guardar cambios`,
      denyButtonText: `No guardar cambios`,
      cancelButtonText: `Cancelar`,
    }).then((result) => {
      /* Read more about isConfirmed, isDenied below */
      if (result.isConfirmed) {
        this.authService.editaPerfil(this.user, this.userId).subscribe(
          data => {
            swal.fire('Editar Perfil', 'Se ha rellenado toda su informacion', 'success').then((result) => {
              if (result.value) {
                window.location.href = '/profile'
              }
            })
          },
          err => {
            this.errorMessage = err.error.message;
          }
        );
        swal.fire('Perfil editado', '', 'success')
      } else if (result.isDenied) {
        swal.fire('Lo cambios no se han guardado', '', 'info').then((result) => {
          if (result.value) {
            window.location.href = '/profile'
          }
        })
      }
    })

  }
}
