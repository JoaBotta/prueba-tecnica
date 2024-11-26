import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '@core/services/auth.service';
import { environment } from 'src/environments/environment';
import { Usuario } from '@core/model/usuario/usuario.model';
import { UsuarioService } from '@core/services/usuario.service';

@Component({
  selector: 'app-personal-details',
  templateUrl: './personal-details.component.html',
  styleUrls: ['./personal-details.component.css']
})
export class PersonalDetailsComponent {
  
  errorMessage: string = '';
  Usuario?: Usuario;
  editMode: boolean = false;
  isAuthenticated: boolean = false;

  registerForm = this.formBuilder.group({
    id: [''],
    lastname: ['', Validators.required],
    firstname: ['', Validators.required],
    country: ['', Validators.required],
    email: [''], // Campo no editable
    username: [''] // Campo no editable
  });
  
  constructor(
    private usuarioService: UsuarioService,
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) {
    this.isAuthenticated = this.authService.isAuthenticated();
  
    if (this.isAuthenticated) {
      this.usuarioService.getUsuario(environment.userId).subscribe({
        next: (usuarioData) => {
          this.Usuario = usuarioData;
          this.registerForm.controls.id.setValue(usuarioData.id.toString());
          this.registerForm.controls.firstname.setValue(usuarioData.firstname);
          this.registerForm.controls.lastname.setValue(usuarioData.lastname);
          this.registerForm.controls.country.setValue(usuarioData.country);
          this.registerForm.controls.email.setValue(usuarioData.email); // Prellenar email
          this.registerForm.controls.username.setValue(usuarioData.username); // Prellenar username
        },
        error: (errorData) => {
          this.errorMessage = 'Error al cargar los datos del usuario.';
          console.error(errorData);
        },
        complete: () => {
          console.info('Datos del usuario cargados correctamente.');
        },
      });
    } else {
      this.router.navigate(['/login']);
    }
  }
  
  

  get firstname() {
    return this.registerForm.controls.firstname;
  }
  

  get lastname() {
    return this.registerForm.controls.lastname;
  }

  get country() {
    return this.registerForm.controls.country;
  }

  // Activar el modo de edición
  enableEditMode() {
    this.editMode = true;
  }

  // Guardar los datos del perfil
  // Guardar los datos del perfil
savePersonalDetailsData() {
  if (this.registerForm.valid) {
    this.usuarioService.updateUsuario(this.registerForm.value as unknown as Usuario).subscribe({
      next: () => {
        console.info('Datos actualizados correctamente.');
        // Recargar la página después de guardar
        location.reload();
      },
      error: (errorData) => {
        this.errorMessage = 'Error al actualizar los datos.';
        console.error(errorData);
      },
    });
  }
}


  // Cancelar la edición
  cancelEdit() {
    this.editMode = false;
    if (this.Usuario) {
      this.registerForm.controls.firstname.setValue(this.Usuario.firstname);
      this.registerForm.controls.lastname.setValue(this.Usuario.lastname);
      this.registerForm.controls.country.setValue(this.Usuario.country);
      this.registerForm.controls.email.setValue(this.Usuario.email); // Restaurar email
      this.registerForm.controls.username.setValue(this.Usuario.username); // Restaurar username
    }
  }

  // Cerrar sesión
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Redirigir al login
  }
}
