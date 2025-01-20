import { Component } from '@angular/core';
import { AuthService } from '@core/services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
/*agregar mat-progress-bar*/
import {MatProgressBarModule} from '@angular/material/progress-bar';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],

})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private authService: AuthService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
  }

  onLogin(): void {
    this.errorMessage = ''; 
    if (this.loginForm.valid) {
      this.authService.login(this.loginForm.value).subscribe({
        next: (response) => {
          this.authService.saveToken(response.token);
          this.router.navigate(['/boliches']);
        },
        error: (err) => {
          // Manejo de errores del backend
          if (err.status === 401) {
            this.errorMessage = 'Correo o contraseña incorrectos.';
          } else if (err.status === 400) {
            this.errorMessage = 'Solicitud inválida. Por favor verifica tus datos.';
          } else {
            this.errorMessage = 'Ha ocurrido un error. Por favor intenta nuevamente.';
          }
        },
      });
    } else {
      // Validaciones del formulario
      if (this.loginForm.get('email')?.invalid) {
        this.errorMessage = 'El correo no tiene un formato válido.';
      } else if (this.loginForm.get('password')?.invalid) {
        this.errorMessage = 'La contraseña debe tener al menos 6 caracteres.';
      }
    }
  }
}
