import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '@core/services/auth.service';
import { Router } from '@angular/router';
import { RegisterRequest } from '@core/model/auth/register-request.model';
import { FormArray } from '@angular/forms';
import { UsuarioService } from '@core/services/usuario.service';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent implements OnInit {

  registerForm!: FormGroup;
  formSubmitted = false;
  selectedRoles: string[] = [];

  countries = [
    { name: 'Argentina', flag: 'https://flagcdn.com/w320/ar.png' },
    { name: 'Brasil', flag: 'https://flagcdn.com/w320/br.png' }
  ];

  rolesDisponibles = ['publica', 'entrada', 'admin'];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      pais: ['', Validators.required],
      roles: this.fb.array([], Validators.required),
      ubicacion: this.fb.group({
        ciudad: [''],
        provincia: ['']
      })
    });
  }

  // Accesos rápidos a controles del form
  get email() { return this.registerForm.get('email'); }
  get username() { return this.registerForm.get('username'); }
  get password() { return this.registerForm.get('password'); }
  get nombre() { return this.registerForm.get('nombre'); }
  get apellido() { return this.registerForm.get('apellido'); }
  get pais() { return this.registerForm.get('pais'); }
  get rolesFormArray() { return this.registerForm.get('roles') as FormArray; }

  toggleRol(rol: string, checked: boolean): void {
    if (checked) {
      if (!this.selectedRoles.includes(rol)) {
        this.selectedRoles.push(rol);
      }
    } else {
      this.selectedRoles = this.selectedRoles.filter(r => r !== rol);
    }
  }
  

  onSubmit(): void {
    this.formSubmitted = true;

    if (this.registerForm.valid) {
      const request: RegisterRequest = {
        ...this.registerForm.value,
        roles: this.rolesFormArray.value
      };
      this.authService.register(request).subscribe(
        () => this.router.navigate(['/login']),
        error => console.error(error)
      );
    }
  }
}
