import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Lecturer } from './lecturer';

@Injectable({
  providedIn: 'root',
})
export class LecturerService {
  private apiUrl = 'http://localhost:8080/lecturer';

  constructor(private http: HttpClient) {}

  getLecturers(): Observable<Lecturer[]> {
    return this.http.get<Lecturer[]>(this.apiUrl);
  }

  getLecturerById(id: string): Observable<Lecturer> {
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Lecturer>(url);
  }
}
