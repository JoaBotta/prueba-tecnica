import { Component, HostListener, EventEmitter, Output } from '@angular/core';
import { navbarData } from './nav-data';
import { Router } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import Swal from 'sweetalert2'; 

interface SideNavToggle {
  screenWidth: number;
  collapsed: boolean;
}

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent {
  @Output() onToggleSideNav: EventEmitter<SideNavToggle> = new EventEmitter();
  collapsed = false;
  screenWidth = window.innerWidth;
  navData = navbarData;

  constructor(private router: Router, private AuthService: AuthService) {}

  @HostListener('window:resize', [])
  onResize(): void {
    this.screenWidth = window.innerWidth;
    if (this.screenWidth > 768 && !this.collapsed) {
      this.collapsed = true; // Expandir automáticamente en pantallas grandes
      this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
    }
  }

  toggleCollapse(): void {
    this.collapsed = !this.collapsed;
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

  closeSidenav(): void {
    this.collapsed = false;
    this.onToggleSideNav.emit({ collapsed: this.collapsed, screenWidth: this.screenWidth });
  }

  handleLogout(): void {
    Swal.fire({
      text: '¿Está seguro de que desea cerrar su sesión?',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      cancelButtonText: 'Cancelar',
      confirmButtonText: 'Cerrar Sesión',
    }).then((result) => {
      if (result.isConfirmed) {
        this.AuthService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
}
