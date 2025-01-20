import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VentaBarraService } from 'src/app/core/services/ventas-barra.service';

@Component({
  selector: 'app-ventas-barra',
  templateUrl: './ventas-barra.component.html',
  styleUrls: ['./ventas-barra.component.css']
})
export class VentasBarraComponent implements OnInit {
  bolicheId!: number;
  barraId!: number;
  ventas: any[] = [];
  barraNombre: string = '';

  constructor(
    private route: ActivatedRoute,
    private ventaBarraService: VentaBarraService
  ) {}

  ngOnInit(): void {
    // Capturar los parámetros de la ruta
    this.bolicheId = Number(this.route.snapshot.paramMap.get('bolicheId'));
    this.barraId = Number(this.route.snapshot.paramMap.get('barraId'));
    this.cargarVentas();
  }

  cargarVentas(): void {
    // Llamar al servicio con el barraId
    this.ventaBarraService.getVentasByBarra(this.barraId).subscribe({
      next: (data) => {
        this.ventas = data;
        if (data.length > 0) {
          this.barraNombre = data[0].barraNombre; // Asegúrate de que el backend proporcione este dato
        }
      },
      error: (err) => {
        console.error('Error al cargar las ventas:', err);
      }
    });
  }
}