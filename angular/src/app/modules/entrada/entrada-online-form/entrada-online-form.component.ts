import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { EntradaOnlineService } from '@core/services/entradaOnline.service';
import { EntradaOnline } from '@core/model/entradaOnline.model';

@Component({
  selector: 'app-entrada-online-form',
  templateUrl: './entrada-online-form.component.html'
})
export class EntradaOnlineFormComponent implements OnInit {
  form!: FormGroup;
  esEdicion = false;
  entradaId!: number;

  constructor(
    private fb: FormBuilder,
    private service: EntradaOnlineService,
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
      this.service.getById(this.entradaId).subscribe(data => {
        this.form.patchValue(data);
      });
    }
  }

  guardar(): void {
    if (this.form.valid) {
      const data: EntradaOnline = this.form.value;
      if (this.esEdicion) {
        this.service.update(this.entradaId, data).subscribe(() => {
          this.router.navigate(['/entrada-online/list']);
        });
      } else {
        this.service.create(data).subscribe(() => {
          this.router.navigate(['/entrada-online/list']);
        });
      }
    }
  }
}
