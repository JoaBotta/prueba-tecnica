import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EntradaService } from '@core/services/entrada.service';
import { Entrada } from '@core/model/entrada.model';

@Component({
  selector: 'app-entrada-form',
  templateUrl: './entrada-form.component.html'
})
export class EntradaFormComponent implements OnInit {
  form!: FormGroup;
  esEdicion = false;
  entradaId!: number;

  constructor(
    private fb: FormBuilder,
    private entradaService: EntradaService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      nombre: ['', Validators.required],
      precio: [0, Validators.required]
    });

    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.esEdicion = true;
      this.entradaId = +id;
      this.entradaService.getById(this.entradaId).subscribe(entrada => {
        this.form.patchValue(entrada);
      });
    }
  }

  guardar(): void {
    if (this.form.valid) {
      const entrada: Entrada = this.form.value;
      if (this.esEdicion) {
        this.entradaService.update(this.entradaId, entrada).subscribe(() => {
          this.router.navigate(['/entrada/list']);
        });
      } else {
        this.entradaService.create(entrada).subscribe(() => {
          this.router.navigate(['/entrada/list']);
        });
      }
    }
  }
}
