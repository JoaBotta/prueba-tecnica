import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { VentaEntradaOnlineService } from '@core/services/venta-entrada-online.service';
import { VentaEntradaOnlineResponse } from '@core/model/venta-entrada-online.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-venta-entrada-online',
  templateUrl: './venta-entrada-online.component.html',
  styleUrls: ['./venta-entrada-online.component.css']
})
export class VentaEntradaOnlineComponent implements OnInit {
  ventas: VentaEntradaOnlineResponse[] = [];
  ventaSeleccionada?: VentaEntradaOnlineResponse;

  constructor(private ventaService: VentaEntradaOnlineService) {}

  ngOnInit(): void {
    this.ventaService.obtenerTodas().subscribe({
      next: (data) => this.ventas = data,
      error: (err) => console.error(err)
    });
  }

  verDetalle(id: number): void {
    this.ventaService.obtenerPorId(id).subscribe({
      next: (data) => this.ventaSeleccionada = data,
      error: (err) => console.error(err)
    });
  }
}
