import { Component, OnInit } from '@angular/core';
import{Rent} from '../rent-admin/rent';
import { Alquiler } from '../_services/alquiler.service';
@Component({
  selector: 'app-comparacion-admin',
  templateUrl: './comparacion-admin.component.html',
  styleUrls: ['./comparacion-admin.component.css']
})
export class ComparacionAdminComponent implements OnInit {
  public rent: Rent = new Rent();
  rents: Rent[];
  rentFind=false;
  constructor(private alquilerService: Alquiler,) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    this.alquilerService.getUnAlquilerCodRef(this.rent.codRefrencia).subscribe(rent => { this.rent = rent;this.rentFind=true})
  }

}
