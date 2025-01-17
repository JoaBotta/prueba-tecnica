import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BolicheService } from '@core/services/boliche.service';
import { Boliche } from '@core/model/boliche.model';

@Component({
  selector: 'app-boliche-form',
  templateUrl: './boliche-form.component.html',
  styleUrls: ['./boliche-form.component.css']
})
export class BolicheFormComponent {
  boliche = {
    nombre: '',
    provincia: '',
    ciudad: '',
    calle: '',
    capacidadMaxima: 0,
  };

  constructor(private bolicheService: BolicheService, private router: Router) {}

  onSubmit(): void {
    this.bolicheService.createBoliche(this.boliche).subscribe({
      next: (response) => {
        console.log('Boliche creado:', response);
        this.router.navigate(['/boliches']); // Redirige al listado de boliches
      },
      error: (err) => {
        console.error('Error al crear el boliche:', err);
      }
    });
  }
}
