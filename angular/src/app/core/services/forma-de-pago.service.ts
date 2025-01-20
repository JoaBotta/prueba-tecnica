// src/app/core/services/forma-de-pago.service.ts

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormaDePago } from '../model/formaDePago.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FormaDePagoService {
  private apiUrl = `${environment.apiUrl}/api/formas-de-pago`;

  constructor(private http: HttpClient) {}

  getFormasDePago(): Observable<FormaDePago[]> {
    return this.http.get<FormaDePago[]>(this.apiUrl);
  }
}
