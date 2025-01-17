import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BolicheService } from '@core/services/boliche.service';
import { Boliche } from '@core/model/boliche.model';


@Component({
  selector: 'app-gestion-boliche',
  templateUrl: './gestion-boliche.component.html',
  styleUrls: ['./gestion-boliche.component.css']
})
export class GestionBolicheComponent implements OnInit {
  bolicheId!: number;
  boliche!: Boliche; // Define el modelo específico

  constructor(
    private route: ActivatedRoute,
    private bolicheService: BolicheService
  ) {}

  ngOnInit(): void {
    this.bolicheId = Number(this.route.snapshot.paramMap.get('id'));

    // Cargar el boliche por ID
    this.bolicheService.getBolicheById(this.bolicheId).subscribe({
      next: (data) => (this.boliche = data),
      error: (err) => console.error('Error al cargar el boliche:', err),
    });
  }
}

