
import { Component, OnInit } from '@angular/core';
import { AuthService } from '@core/services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterRequest } from '@core/model/auth/register-request.model';

@Component({
  selector: 'app-crear-usuario',
  templateUrl: './crear-usuario.component.html',
  styleUrls: ['./crear-usuario.component.css']
})
export class CrearUsuarioComponent {

  registerForm!: FormGroup;
  formSubmitted = false;

  // Lista estática de países
  countries = [
    { name: 'Argentina', flag: 'https://flagcdn.com/w320/ar.png' },
    { name: 'Brasil', flag: 'https://flagcdn.com/w320/br.png' }
  ];

  // Lista de roles disponibles
  roles = [
    { name: 'DUEÑO', value: 'DUEÑO' },
    { name: 'PUBLICO', value: 'PUBLICO' },
    { name: 'ENTRADA', value: 'ENTRADA' }
  ];

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Configuración del formulario
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      country: ['', Validators.required],
      role: ['', Validators.required]  // Campo para el rol
    });
  }

  get email() { return this.registerForm.get('email'); }
  get username() { return this.registerForm.get('username'); }
  get password() { return this.registerForm.get('password'); }
  get firstname() { return this.registerForm.get('firstname'); }
  get lastname() { return this.registerForm.get('lastname'); }
  get country() { return this.registerForm.get('country'); }
  get role() { return this.registerForm.get('role'); }  // Getter para el campo role

  onSubmit(): void {
    this.formSubmitted = true;

    if (this.registerForm.valid) {
      const registerRequest: RegisterRequest = this.registerForm.value;
      this.authService.register(registerRequest).subscribe(
        () => this.router.navigate(['/login']),
        error => console.error(error)
      );
    }
  }
}
