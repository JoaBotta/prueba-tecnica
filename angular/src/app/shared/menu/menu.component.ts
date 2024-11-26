import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { Usuario } from '@core/model/usuario/usuario.model';
import { UsuarioService } from '@core/services/usuario.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  usuario?: Usuario; // Usuario logueado

  constructor(
    private usuarioService: UsuarioService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Obtener usuario logueado
    this.usuarioService.getUsuario(environment.userId).subscribe({
      next: (usuarioData) => {
        this.usuario = usuarioData;
      },
      error: (error) => {
        console.error('Error al obtener usuario:', error);
      },
    });

  }
  
}
