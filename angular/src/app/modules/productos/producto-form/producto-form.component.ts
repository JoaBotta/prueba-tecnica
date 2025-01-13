import { Component, OnInit } from '@angular/core';
import { ProductoService } from '@core/services/producto.service';
import {Producto} from '@core/model/producto.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-producto-form',
  templateUrl: './producto-form.component.html',
  styleUrls: ['./producto-form.component.css']
})
export class ProductoFormComponent implements OnInit {
  producto: Producto = { nombre: '', precioUnitario: 0, descripcion: '' };
  editMode = false;

  constructor(
    private productoService: ProductoService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.editMode = true;
      this.productoService.obtenerProductoPorId(+id).subscribe((data) => {
        this.producto = data;
      });
    }
  }

  guardarProducto(): void {
    if (this.editMode) {
      this.productoService
        .actualizarProducto(this.producto.id!, this.producto)
        .subscribe(() => this.router.navigate(['/productos']));
    } else {
      this.productoService.crearProducto(this.producto).subscribe(() => {
        this.router.navigate(['/productos']);
      });
    }
  }
}
