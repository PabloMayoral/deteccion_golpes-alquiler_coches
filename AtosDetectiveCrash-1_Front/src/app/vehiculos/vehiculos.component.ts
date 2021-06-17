import { Component, OnInit } from '@angular/core';
import { Vehiculos } from '../_services/vehiculos.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { Vehiculo } from '../vehiculos/vehiculos';
import swal from 'sweetalert2';


@Component({
  selector: 'app-vehiculos',
  templateUrl: './vehiculos.component.html',
  //styleUrls: ['./vehiculos.component.css']
})

export class VehiculosComponent implements OnInit {
  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  vehiculo: Vehiculo = new Vehiculo();
  selectedFile: File
  imageLoad: Boolean = false
  isLoggedIn = false;
  currentUser: any;
  url = "";
  isSuccessful = true;
  constructor(private vehiculosService: Vehiculos, private http: HttpClient, private token: TokenStorageService) { }
  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      this.selectedFile = event.target.files[0]
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event) => {
        this.url = event.target.result as string
      }
      this.imageLoad = true
    }
  }
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.isLoggedIn = !!this.token.getToken();
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
  }

  onSubmit(): void {
    if (this.imageLoad) {
      this.vehiculo.alquilado='0'
      this.vehiculosService.registraVehiculos(this.vehiculo).subscribe(
        err => {
          this.isSuccessful = false
        }
      );

      const uploadData = new FormData();
      uploadData.append('file', this.selectedFile, this.selectedFile.name);
      this.http.post('http://localhost:5000/vehiculos/upload', uploadData).subscribe(
        data => {},
        err => {
          swal.fire('Coche', 'La foto no se subio de forma correcta', 'error').then((result) => {
            if (result.value) {
              this.isSuccessful = false;
              window.location.reload;
            }
          })
        }
      );
    } else {
      swal.fire('Coche', 'Falta subir una foto', 'question')
    }
    if (this.isSuccessful) {
      console.log('feuefb')
      swal.fire('Coche', 'Se añadió correctamente el coche', 'success').then((result) => {
        if (result.value) {
          window.location.href='/vehiculos';
        }
      })
    }

  }
}
