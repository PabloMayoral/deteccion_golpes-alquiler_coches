import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpHeaders } from '@angular/common/http';
import swal from 'sweetalert2';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'multipart/form-data',
  })
};

@Component({
  selector: 'app-crash-detector',
  templateUrl: './crash-detector.component.html',
  styleUrls: ['./crash-detector.component.css']
})
export class CrashDetectorComponent implements OnInit {
  final_file: File
  initial_file: File
  imageInitialLoad: Boolean = false
  url = "";
  cod_ref: string = '';
  isLoggedIn = false;
  onSelectFileInitial(event) {
    if (event.target.files && event.target.files[0]) {
      this.initial_file = event.target.files[0]
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event) => {
        this.url = event.target.result as string
      }
      this.imageInitialLoad = true
    }
  }

  onSubmit() {
    if (this.imageInitialLoad) {
      const uploadData: FormData = new FormData()
      uploadData.append('initial_file', this.initial_file, this.initial_file.name);
      this.http.post('http://localhost:5000/alquileres/upload/' + this.cod_ref, uploadData, { observe: 'response' }).subscribe(
        response => {
          if (response.status == 201) {
            swal.fire('Alquiler', 'Ya estan ambas fotos subidas', 'error').then((result) => {
              if (result.value) {
                window.location.href = '/detection'
              }
            })
          }
          else {
            swal.fire('Alquiler', 'Se ha subido la foto perfectamente', 'success').then((result) => {
              if (result.value) {
                window.location.href = '/detection'
              }
            })
          }
        },
        err => {
          swal.fire('Alquiler', 'La foto no se subio de forma correcta', 'error')
        }
      );
    }
    else {
      alert('Sube un archivo')
    }
  }
  constructor(private http: HttpClient, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.token.getToken();
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
  }

}
