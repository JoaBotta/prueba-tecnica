
import { Component, OnInit } from '@angular/core';
import { BolicheService } from '@core/services/boliche.service';
import { Boliche } from '@core/model/boliche.model';
import { Router } from '@angular/router';


@Component({
  selector: 'app-boliche',
  templateUrl: './boliche.component.html',
  styleUrls: ['./boliche.component.css'],
})
export class BolicheComponent implements OnInit {
  boliches: Boliche[] = [];
  newBoliche: Boliche = {
    nombre: '',
    provincia: '',
    ciudad: '',
    calle: '',
    capacidadMaxima: 0,
  };

  constructor(private bolicheService: BolicheService, private router: Router) {}

  ngOnInit(): void {
    this.loadBoliches();
  }

  loadBoliches(): void {
    this.bolicheService.getAllBoliches().subscribe((data) => {
      this.boliches = data;
    });
  }

  addBoliche(): void {
    if (this.newBoliche.nombre && this.newBoliche.provincia) {
      this.bolicheService.createBoliche(this.newBoliche).subscribe(() => {
        this.loadBoliches(); // Recargar la lista
        this.newBoliche = {
          nombre: '',
          provincia: '',
          ciudad: '',
          calle: '',
          capacidadMaxima: 0,
        };
      });
    }
  }
}
