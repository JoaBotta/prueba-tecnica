import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from '@core/model/cliente.model';
import { Lista } from '@core/model/lista.model';
import { ClienteService } from '@core/services/cliente.service';
import { ListaService } from '@core/services/lista.service';

@Component({
  selector: 'app-cliente-list',
  templateUrl: './cliente-list.component.html',
  styleUrls: ['./cliente-list.component.css']
})
export class ClienteListComponent implements OnInit {
  @Input() listaId!: number;
  clientes: Cliente[] = [];
  lista!: Lista;



  constructor(
    private route: ActivatedRoute,
    private listaService: ListaService,
    private clienteService: ClienteService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.listaService.getLista(id).subscribe(data => {
      this.lista = data;
    });
  }

  marcarAsistencia(cliente: Cliente, presente: boolean): void {
    this.clienteService.actualizarAsistencia(cliente.id!, presente).subscribe(() => {
      cliente.asistencia = presente;
    });
  }
}




  
