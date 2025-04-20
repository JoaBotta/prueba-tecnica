import { Component, OnInit } from '@angular/core';
import { EntradaOnline } from '@core/model/entradaOnline.model';
import { EntradaOnlineService } from '@core/services/entradaOnline.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-entrada-online-list',
  templateUrl: './entrada-online-list.component.html',
  styleUrls: ['./entrada-online-list.component.css']
})
export class EntradaOnlineListComponent implements OnInit {
  entradas: EntradaOnline[] = [];

  bolicheId!: number;
  constructor(private service: EntradaOnlineService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.cargarEntradas();
    this.bolicheId = Number(this.route.snapshot.paramMap.get('id'));
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
