import { Component, OnInit } from '@angular/core';
import { EntradaService } from '@core/services/entrada.service';
import { Entrada } from '@core/model/entrada.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-entrada-list',
  templateUrl: './entrada-list.component.html'
})
export class EntradaListComponent implements OnInit {
  entradas: Entrada[] = [];

  constructor(private entradaService: EntradaService, private router: Router) {}

  ngOnInit(): void {
    this.obtenerEntradas();
  }

  obtenerEntradas(): void {
    this.entradaService.getAll().subscribe(data => this.entradas = data);
  }

  

  eliminar(id: number): void {
    if (confirm('¿Estás seguro de eliminar esta entrada?')) {
      this.entradaService.delete(id).subscribe(() => this.obtenerEntradas());
    }
  }
}
