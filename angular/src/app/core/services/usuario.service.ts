/*
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Usuario } from '../model/usuario/usuario.model';
import { UsuarioRequest } from '../model/usuario/usuario-request.model';
import { UsuarioResponse } from '../model/usuario/usuario-response.model';
@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) {}

  getUsuario(id:number):Observable<Usuario>{
    return this.http.get<Usuario>(environment.urlApi+"Usuario/"+id).pipe(
      catchError(this.handleError)
    )
  }

  updateUsuario(usuarioRequest:Usuario):Observable<any>
  {
    return this.http.put(environment.urlApi+"Usuario", usuarioRequest).pipe(
      catchError(this.handleError)
    )
  }

  private handleError(error:HttpErrorResponse){
    if(error.status===0){
      console.error('Se ha producio un error ', error.error);
    }
    else{
      console.error('Backend retornó el código de estado ', error.status, error.error);
    }
    return throwError(()=> new Error('Algo falló. Por favor intente nuevamente.'));
  }
}*/

// usuario.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../model/usuario/usuario.model';
import { environment } from 'src/environments/environment';
import { RegisterRequest } from '../model/auth/register-request.model';


@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private apiUrl = `${environment.apiUrl}/api/usuario`;


  constructor(private http: HttpClient) {}

  getUsuario(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`${this.apiUrl}/${id}`);
  }

  updateUsuario(usuario: Usuario): Observable<any> {
    return this.http.put(this.apiUrl, usuario);
  }
  
  getUsuarioActual(): Observable<any> {
    return this.http.get(`${this.apiUrl}/me`);
  }
  registerUser(data: RegisterRequest): Observable<any> {
    return this.http.post(`${this.apiUrl}`, data);
  }
}

