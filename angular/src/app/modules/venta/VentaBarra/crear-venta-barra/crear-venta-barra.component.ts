import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VentaBarraService } from 'src/app/core/services/ventas-barra.service';
import { ProductoService } from 'src/app/core/services/producto.service';
import { FormaDePagoService } from 'src/app/core/services/forma-de-pago.service';
import { Producto } from 'src/app/core/model/producto.model';
import { FormaDePago } from 'src/app/core/model/formaDePago.model';
import { UsuarioService } from 'src/app/core/services/usuario.service';  // Asegúrate de importar el servicio
import { Barra } from '@core/model/barra.model';

@Component({
  selector: 'app-crear-venta-barra',
  templateUrl: './crear-venta-barra.component.html',
  styleUrls: ['./crear-venta-barra.component.css']
})
export class CrearVentaBarraComponent implements OnInit {
  bolicheId!: number;
  barraId!: number;
  productos: Producto[] = [];
  formasDePago: FormaDePago[] = [];
  selectedFormaDePago!: FormaDePago;
  selectedProductos: any[] = [];  // Contendrá los productos seleccionados y sus cantidades
  totalVenta: number = 0;
  usuario: any;  // Variable para almacenar los datos del usuario
  errorMessage: string = '';  // Agregar propiedad para almacenar el mensaje de error
  venta: any = {};  // Agregar propiedad para almacenar el objeto venta
  // Definir las propiedades 'producto' y 'cantidad'
  producto!: Producto;
  cantidad: number = 1; // Establecer un valor inicial para la cantidad

  constructor(
    private route: ActivatedRoute,
    private ventaBarraService: VentaBarraService,
    private productoService: ProductoService,
    private formaDePagoService: FormaDePagoService,
    private router: Router,
    private usuarioService: UsuarioService  // Inyectar el servicio de usuario
  ) {}

  ngOnInit(): void {
    this.bolicheId = Number(this.route.snapshot.paramMap.get('bolicheId'));
    this.barraId = Number(this.route.snapshot.paramMap.get('barraId'));
    this.cargarProductos();
    this.cargarFormasDePago();
    this.obtenerUsuario();  // Obtener los datos del usuario
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

  // Método para obtener los datos del usuario
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

  // Método para incrementar la cantidad de un producto

  // Método para agregar un producto a la venta
  agregarProducto(): void {
    // Verificar si ya existe este producto en el detalle
    const productoExistente = this.selectedProductos.find(item => item.producto.id === this.producto.id);
  
    if (productoExistente) {
      // Si el producto ya está en el detalle, solo actualizar la cantidad
      productoExistente.cantidad += this.cantidad;
      // Actualizar el subtotal de ese producto
      productoExistente.subTotal = productoExistente.producto.precioUnitario * productoExistente.cantidad;
    } else {
      // Si el producto no está en el detalle, agregarlo
      const subTotal = this.producto.precioUnitario * this.cantidad;
      this.selectedProductos.push({ producto: this.producto, cantidad: this.cantidad, subTotal });
    }
  
    // Actualizar el total de la venta
    this.calcularTotal();
  
    // Resetear la cantidad para que el usuario pueda agregar otro producto si lo desea
    this.cantidad = 1;
  }
  
  // Calcular el total de la venta
  calcularTotal(): void {
    this.totalVenta = this.selectedProductos.reduce((acc, item) => acc + item.subTotal, 0);
  }

  // Método para crear la venta
  // Método para crear la venta
 // Método para crear la venta
 crearVenta(): void {
  const venta = {
    barraId: this.barraId,
    vendedoraNombre: this.usuario?.username,
    formaDePago: this.selectedFormaDePago,
    total: this.totalVenta,
    fecha: new Date().toISOString(),
    detalleVenta: this.selectedProductos.map(item => ({
      productoNombre: item.producto.nombre,
      cantidad: item.cantidad,
      subTotal: item.subTotal
    }))
  };
  
  // Asignar el objeto venta a la propiedad para mostrarlo en el HTML
  this.venta = venta;

  this.ventaBarraService.createVenta(venta).subscribe({
    next: (data) => {
      console.log('Venta creada exitosamente', data);
      this.router.navigate([`/boliche/${this.bolicheId}/barras/${this.barraId}/ventas-barra`]);
    },
    error: (err) => {
      console.error('Error al crear la venta:', err);
      this.errorMessage = `Error al crear la venta: ${err.message || err.statusText}`;
    }
  });
}
}
