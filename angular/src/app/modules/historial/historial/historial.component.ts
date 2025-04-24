import { Component, OnInit } from '@angular/core';
import { HistorialAsistencia } from '@core/model/historial-asistencia.model';
import { AsistenciaService } from '@core/services/asistencia.service';

@Component({
  selector: 'app-historial',
  templateUrl: './historial.component.html',
  styleUrls: ['./historial.component.css']
})
export class HistorialComponent implements OnInit {
  historial: HistorialAsistencia[] = [];

  constructor(private asistenciaService: AsistenciaService) {}

  ngOnInit(): void {
    this.asistenciaService.getHistoriales().subscribe(data => this.historial = data);
  }
}
