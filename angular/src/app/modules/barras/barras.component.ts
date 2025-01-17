import { Component, OnInit } from '@angular/core';
import { Barra } from '@core/model/barra.model';
import { BarraService } from '@core/services/barra.service';
import { ActivatedRoute } from '@angular/router';

import { BolicheService } from '@core/services/boliche.service';
import { Boliche } from '@core/model/boliche.model';

@Component({
  selector: 'app-barras',
  templateUrl: './barras.component.html',
  styleUrls: ['./barras.component.css']
})
export class BarrasComponent implements OnInit {
  bolicheId!: number;
  barras: any[] = [];
  bolicheNombre: string = '';
  mostrarFormulario: boolean = false;

  nuevaBarra = {
    nombre: '',
  };

  constructor(private route: ActivatedRoute, private barraService: BarraService) {}

  ngOnInit(): void {
    this.bolicheId = Number(this.route.snapshot.paramMap.get('id'));
    this.cargarBarras();
  }

  cargarBarras(): void {
    this.barraService.getBarrasByBoliche(this.bolicheId).subscribe({
      next: (data) => {
        this.barras = data;
        if (data.length > 0) {
          this.bolicheNombre = data[0].bolicheNombre;
        }
      },
      error: (err) => {
        console.error('Error al cargar las barras:', err);
      },
    });
  }

  crearBarra(): void {
    const barraData = {
      nombre: this.nuevaBarra.nombre,
      bolicheId: this.bolicheId,
    };

    this.barraService.createBarra(barraData).subscribe({
      next: (nuevaBarra) => {
        this.barras.push(nuevaBarra);
        this.nuevaBarra.nombre = '';
        this.mostrarFormulario = false;
      },
      error: (err) => {
        console.error('Error al crear la barra:', err);
      },
    });
  }
}
