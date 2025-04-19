import { Component, OnInit } from '@angular/core';
import { EntradaOnline } from '@core/model/entradaOnline.model';
import { EntradaOnlineService } from '@core/services/entradaOnline.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-entrada-online-list',
  templateUrl: './entrada-online-list.component.html'
})
export class EntradaOnlineListComponent implements OnInit {
  entradas: EntradaOnline[] = [];

  constructor(private service: EntradaOnlineService) {}

  ngOnInit(): void {
    this.cargarEntradas();
  }

  cargarEntradas(): void {
    this.service.getAll().subscribe(data => this.entradas = data);
  }

  eliminar(id: number): void {
    if (confirm('¿Eliminar esta entrada?')) {
      this.service.delete(id).subscribe(() => this.cargarEntradas());
    }
  }
}
