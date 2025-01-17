import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boliche } from '../model/boliche.model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BolicheService {
  private apiUrl = `${environment.apiUrl}/api/boliches`;

  constructor(private http: HttpClient) {}

  getAllBoliches(): Observable<Boliche[]> {
    return this.http.get<Boliche[]>(this.apiUrl);
  }

  createBoliche(boliche: Boliche): Observable<Boliche> {
    return this.http.post<Boliche>(this.apiUrl, boliche);
  }
  getBolicheById(id: number): Observable<Boliche> {
    return this.http.get<Boliche>(`${this.apiUrl}/${id}`);
  }
}