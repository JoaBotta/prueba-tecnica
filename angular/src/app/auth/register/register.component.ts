import { Component, OnInit } from '@angular/core';
import { AuthService } from '@core/services/auth.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegisterRequest } from '@core/model/auth/register-request.model';
import { CountryService } from '@core/services/country.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm!: FormGroup;
  formSubmitted = false;
  countries: any[] = []; // Arreglo para almacenar los países

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private countryService: CountryService 
  ) {}

  ngOnInit(): void {
    // Configuración del formulario
    this.registerForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(8)]],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      country: ['', Validators.required]
    });
    

    // Obtener la lista de países
    this.countryService.getCountries().subscribe(
      data => {
        this.countries = data.map(country => ({
          name: country.name.common,
          flag: country.flags.svg
        }));
      },
      error => console.error('Error fetching countries:', error)
    );
  }

  get email() { return this.registerForm.get('email'); }
  get username() { return this.registerForm.get('username'); }
  get password() { return this.registerForm.get('password'); }
  get firstname() { return this.registerForm.get('firstname'); }
  get lastname() { return this.registerForm.get('lastname'); }
  get country() { return this.registerForm.get('country'); }

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
