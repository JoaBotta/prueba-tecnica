import { Component, OnInit } from '@angular/core';
import { Lista } from '@core/model/lista.model';
import { ListaResumen } from '@core/model/listaResumen.model';
import { ListaService } from '@core/services/lista.service';

@Component({
  selector: 'app-lista-list',
  templateUrl: './lista-list.component.html',
  styleUrls: ['./lista-list.component.css']
})
export class ListaListComponent implements OnInit {
  listas: ListaResumen[] = [];

  constructor(private listaService: ListaService) {}

  ngOnInit(): void {
    this.listaService.getResumenListas().subscribe(data => {
      this.listas = data;
    });
  }
}