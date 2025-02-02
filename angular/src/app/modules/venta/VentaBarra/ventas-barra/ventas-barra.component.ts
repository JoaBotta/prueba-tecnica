import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { VentaBarraService } from 'src/app/core/services/ventas-barra.service';
import Swal from 'sweetalert2'; // Importar SweetAlert2 para confirmaciones

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

  // Función para eliminar una venta
  eliminarVenta(ventaId: number): void {
    // Confirmar antes de eliminar
    Swal.fire({
      title: '¿Estás seguro?',
      text: 'No podrás revertir esta acción',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        // Llamar al servicio para eliminar la venta
        this.ventaBarraService.deleteVenta(ventaId).subscribe({
          next: () => {
            // Mostrar mensaje de éxito
            Swal.fire('Eliminada', 'La venta ha sido eliminada correctamente', 'success');
            // Recargar la lista de ventas
            this.cargarVentas();
          },
          error: (err) => {
            console.error('Error al eliminar la venta:', err);
            Swal.fire('Error', 'No se pudo eliminar la venta', 'error');
          }
        });
      }
    });
  }
}