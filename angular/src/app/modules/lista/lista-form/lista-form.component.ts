import { Component } from '@angular/core';
import { Lista } from '@core/model/lista.model';
import { ListaService } from '@core/services/lista.service';

@Component({
  selector: 'app-lista-form',
  templateUrl: './lista-form.component.html',
  styleUrls: ['./lista-form.component.css']
})
export class ListaFormComponent {
  lista: Lista = { nombre: '', fecha: new Date().toISOString().split('T')[0] };

  constructor(private listaService: ListaService) {}

  crearLista(): void {
    this.listaService.crearLista(this.lista).subscribe(() => {
      alert('Lista creada');
      this.lista = { nombre: '', fecha: new Date().toISOString().split('T')[0] };
    });
  }
}