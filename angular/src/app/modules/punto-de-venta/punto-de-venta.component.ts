import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PuntoDeVentaService } from '@core/services/punto-de-venta.service';
import { PuntoDeVenta, PuntoDeVentaRequest } from '@core/model/punto-de-venta.model';

@Component({
  selector: 'app-punto-de-venta',
  templateUrl: './punto-de-venta.component.html',
  styleUrls: ['./punto-de-venta.component.css'],
})
export class PuntoDeVentaComponent implements OnInit {
  bolicheId!: number;
  usuarioId: number = 2; // ❗ Simulación del usuario logueado, reemplázalo con el real
  puntosDeVenta: PuntoDeVenta[] = [];
  mostrarFormulario: boolean = false;

  nuevoPuntoDeVenta: PuntoDeVentaRequest = {
    nombre: '',
    seguridadId: this.usuarioId, // ✅ Enviar el ID del usuario autenticado
    bolicheId: 0,
  };

  constructor(
    private route: ActivatedRoute,
    private puntoDeVentaService: PuntoDeVentaService
  ) {}

  ngOnInit(): void {
    this.bolicheId = Number(this.route.snapshot.paramMap.get('bolicheId'));
    this.nuevoPuntoDeVenta.bolicheId = this.bolicheId; // Asignar el bolicheId al nuevo punto de venta
    this.nuevoPuntoDeVenta.seguridadId = this.usuarioId; // ✅ Asignar el ID del usuario autenticado
    this.cargarPuntosDeVenta();
  }

  cargarPuntosDeVenta(): void {
    console.log("Cargando puntos de venta...");
    this.puntoDeVentaService.getPuntosDeVentaByBoliche(this.bolicheId).subscribe({
      next: (data) => {
        this.puntosDeVenta = data;
      },
      error: (err) => {
        console.error('Error al cargar los puntos de venta:', err);
      },
    });
  }

  crearPuntoDeVenta(): void {
    console.log("Enviando datos:", this.nuevoPuntoDeVenta);
    this.puntoDeVentaService.createPuntoDeVenta(this.nuevoPuntoDeVenta).subscribe({
      next: (nuevoPuntoDeVenta) => {
        this.puntosDeVenta.push(nuevoPuntoDeVenta);
        this.nuevoPuntoDeVenta = { nombre: '', seguridadId: this.usuarioId, bolicheId: this.bolicheId }; // Resetear el formulario
        this.mostrarFormulario = false; // Ocultar el formulario después de crear
      },
      error: (err) => {
        console.error('Error al crear el punto de venta:', err);
      },
    });
  }

  eliminarPuntoDeVenta(id: number): void {
    this.puntoDeVentaService.deletePuntoDeVenta(id).subscribe({
      next: () => {
        this.puntosDeVenta = this.puntosDeVenta.filter((p) => p.id !== id);
      },
      error: (err) => {
        console.error('Error al eliminar el punto de venta:', err);
      },
    });
  }
}
