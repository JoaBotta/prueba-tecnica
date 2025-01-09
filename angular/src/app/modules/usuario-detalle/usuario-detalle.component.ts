import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import { environment } from 'src/environments/environment';
import { Usuario } from '@core/model/usuario/usuario.model';
import { UsuarioService } from '@core/services/usuario.service';

@Component({
  selector: 'app-usuario-detalle',
  templateUrl: './usuario-detalle.component.html',
  styleUrls: ['./usuario-detalle.component.css']
})
export class UsuarioDetalleComponent implements OnInit {
  usuario: Usuario | null = null;

  constructor(private usuarioService: UsuarioService) {}

  ngOnInit(): void {
    this.obtenerUsuario();
  }

  obtenerUsuario(): void {
    this.usuarioService.getUsuarioActual().subscribe({
      next: (data) => (this.usuario = data),
      error: (err) => console.error('Error al obtener el usuario:', err),
    });
  }
}
