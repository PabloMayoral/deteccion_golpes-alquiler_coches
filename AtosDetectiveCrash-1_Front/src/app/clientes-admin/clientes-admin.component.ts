import { Component, OnInit } from '@angular/core';
import { Clientes } from '../clientes-admin/clientes';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-clientes-admin',
  templateUrl: './clientes-admin.component.html',
  styleUrls: ['./clientes-admin.component.css']
})
export class ClientesAdminComponent implements OnInit {
  clientes: Clientes[];
  constructor(private clienteService: AuthService) { }

  ngOnInit(): void {
    this.clienteService.getClientes().subscribe(clientes => { this.clientes = clientes; }
    );
  }

}
