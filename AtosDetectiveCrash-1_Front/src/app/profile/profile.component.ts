import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { AuthService } from '../_services/auth.service';
import { Clientes } from '../clientes-admin/clientes'
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  private user: Clientes = new Clientes();
  userId = ''
  currentUser: any;
  isLoggedIn = false;

  constructor(private token: TokenStorageService, private authService: AuthService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.userId = this.currentUser.id
    this.isLoggedIn = !!this.token.getToken();
    if (!this.isLoggedIn) {
      window.location.href = '/login'
    }
    this.authService.getCliente(this.userId).subscribe(user => this.user = user)
  }
}
