import { Component, OnInit } from '@angular/core';
import { ProductoService } from '@core/services/producto.service'
import {Producto} from '@core/model/producto.model'

@Component({
  selector: 'app-producto-lista',
  templateUrl: './producto-lista.component.html',
  styleUrls: ['./producto-lista.component.css']
})

export class ProductoListaComponent implements OnInit {
  productos: Producto[] = [];

  constructor(private productoService: ProductoService) {}

  ngOnInit(): void {
    this.cargarProductos();
  }

  cargarProductos(): void {
    this.productoService.listarProductos().subscribe((data) => {
      this.productos = data;
    });
  }

  eliminarProducto(id: number): void {
    this.productoService.eliminarProducto(id).subscribe(() => {
      this.cargarProductos();
    });
  }
}

