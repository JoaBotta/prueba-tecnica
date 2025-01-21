import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VentaBarraService } from 'src/app/core/services/ventas-barra.service';
import { ProductoService } from 'src/app/core/services/producto.service';
import { FormaDePagoService } from 'src/app/core/services/forma-de-pago.service';
import { Producto } from 'src/app/core/model/producto.model';
import { FormaDePago } from 'src/app/core/model/formaDePago.model';
import { UsuarioService } from 'src/app/core/services/usuario.service';  // Asegúrate de importar el servicio
import { Barra } from '@core/model/barra.model';
import { ImpresoraTermicaService } from '@core/services/ImpresoraTermica.Service'; // Importamos el servicio
import Swal from 'sweetalert2';
import { BarraService } from 'src/app/core/services/barra.service';

@Component({
  selector: 'app-crear-venta-barra',
  templateUrl: './crear-venta-barra.component.html',
  styleUrls: ['./crear-venta-barra.component.css']
})
export class CrearVentaBarraComponent implements OnInit {
  bolicheId!: number;
  barraId!: number;
  ventaId!: number;
  barra: any;
  productos: Producto[] = [];
  formasDePago: FormaDePago[] = [];
  selectedFormaDePago!: FormaDePago;
  selectedProductos: any[] = [];  // Contendrá los productos seleccionados y sus cantidades
  totalVenta: number = 0;
  usuario: any;  // Variable para almacenar los datos del usuario
  errorMessage: string = '';  // Agregar propiedad para almacenar el mensaje de error
  venta: any = {};  // Agregar propiedad para almacenar el objeto venta
  producto!: Producto;
  cantidad: number = 1; // Establecer un valor inicial para la cantidad
  ventaCreada: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private ventaBarraService: VentaBarraService,
    private productoService: ProductoService,
    private BarraService : BarraService,
    private formaDePagoService: FormaDePagoService,
    private router: Router,
    private usuarioService: UsuarioService,  // Inyectar el servicio de usuario
    private impresoraService: ImpresoraTermicaService // Inyectamos el servicio
  ) {}
  

  ngOnInit(): void {
    this.bolicheId = Number(this.route.snapshot.paramMap.get('bolicheId'));
    this.barraId = Number(this.route.snapshot.paramMap.get('barraId'));
    this.cargarProductos();
    this.cargarFormasDePago();
    this.obtenerUsuario();

  }

  cargarProductos(): void {
    this.productoService.listarProductos().subscribe({
      next: (data) => {
        this.productos = data;
      },
      error: (err) => {
        console.error('Error al cargar productos:', err);
      }
    });
  }

  cargarFormasDePago(): void {
    this.formaDePagoService.getFormasDePago().subscribe({
      next: (data) => {
        this.formasDePago = data;
      },
      error: (err) => {
        console.error('Error al cargar formas de pago:', err);
      }
    });
  }

  obtenerUsuario(): void {
    this.usuarioService.getUsuarioActual().subscribe({
      next: (data) => {
        this.usuario = data;  // Asignar los datos del usuario a la propiedad
      },
      error: (err) => {
        console.error('Error al obtener usuario:', err);
      }
    });
  }

  getBarras(): void {
    this.BarraService.getBarrasByBoliche(this.bolicheId).subscribe({
      next: (data) => {
        this.barra = data.find((barra: Barra) => barra.id === this.barraId);
      },
      error: (err) => {
        console.error('Error al cargar la barra:', err);
      }
    });
  }

  agregarProducto(): void {
    const productoExistente = this.selectedProductos.find(item => item.producto.id === this.producto.id);
  
    if (productoExistente) {
      productoExistente.cantidad += this.cantidad;
      productoExistente.subTotal = productoExistente.producto.precioUnitario * productoExistente.cantidad;
    } else {
      const subTotal = this.producto.precioUnitario * this.cantidad;
      this.selectedProductos.push({ producto: this.producto, cantidad: this.cantidad, subTotal });
    }
  
    this.calcularTotal();
    this.cantidad = 1;
  }

  calcularTotal(): void {
    this.totalVenta = this.selectedProductos.reduce((acc, item) => acc + item.subTotal, 0);
  }

  crearVenta(): void {
    const venta = {
      barraId: this.barraId,
      vendedoraId: this.usuario?.id,
      formaDePagoId: this.selectedFormaDePago.id,
      total: this.totalVenta,
      fecha: new Date().toISOString(),
      detalleVenta: this.selectedProductos.map(item => ({
        productoId: item.producto.id,
        cantidad: item.cantidad
      }))
    };
  
    this.ventaBarraService.createVenta(venta).subscribe({
      next: (data) => {
        console.log('Venta creada exitosamente', data);
  
        // Guardar el id de la venta
        this.ventaId = data.id;  // Asumir que el id está en data.id, verifica según la respuesta del backend
  
        // Mostrar Swal.fire para preguntar si se desea imprimir el recibo
        Swal.fire({
          title: '¿Deseas imprimir el recibo?',
          showCancelButton: true,
          confirmButtonText: 'Sí, Imprimir',
          cancelButtonText: 'No, Gracias',
        }).then((result) => {
          if (result.isConfirmed) {
            this.imprimirRecibo();  // Si se confirma, imprimir el recibo
          }
          this.router.navigate([`/boliche/${this.bolicheId}/barras/${this.barraId}/ventas-barra`]);
        });
      },
      error: (err) => {
        console.error('Error al crear la venta:', err);
        this.errorMessage = `Error al crear la venta: ${err.message || err.statusText}`;
      }
    });
  }
  
  imprimirRecibo(): void {
    let recibo = '=== Recibo de Venta ===\n';
    recibo += `Nro Ticket: ${this.ventaId}\n`;  // Aquí se utiliza el id de la venta
    recibo += `Barra: ${this.barraId}\n`;
    recibo += `Fecha: ${new Date().toLocaleString()}\n`;
    recibo += `Vendedora: ${this.usuario?.username || 'Desconocido'}\n\n`;
    recibo += 'Productos:\n';
  
    this.selectedProductos.forEach(item => {
      recibo += `${item.producto.nombre} x${item.cantidad} $${item.subTotal}\n`;
    });
  
    recibo += `\nTotal: $${this.totalVenta}\n`;
    recibo += '=======================\n';
  
    this.impresoraService.imprimirRecibo(recibo);
  }
  

  
}

