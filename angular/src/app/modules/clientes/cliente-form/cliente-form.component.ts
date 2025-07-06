import { Component, Input } from '@angular/core';
import { Cliente } from '@core/model/cliente.model';
import { ClienteService } from '@core/services/cliente.service';

@Component({
  selector: 'app-cliente-form',
  templateUrl: './cliente-form.component.html',
  styleUrls: ['./cliente-form.component.css']
})
export class ClienteFormComponent {
  @Input() listaId!: number;
  cliente: Cliente = {
    nombre: '',
    apellido: '',
    documento: '',
    telefono: '',
    email: '',
    estadoId: 0,
  };


  constructor(private clienteService: ClienteService) {}

  agregarCliente(): void {
    this.clienteService.agregarCliente(this.listaId, this.cliente).subscribe(() => {
      alert('Cliente agregado');
      this.cliente = {
      nombre: '',
      apellido: '',
      documento: '',
      telefono: '',
      email: '',
      estadoId: 0
    };
    });
  }
}
