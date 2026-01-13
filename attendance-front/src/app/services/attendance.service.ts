import { Injectable, inject } from '@angular/core'; 
import { HttpClient } from '@angular/common/http'; 
 
@Injectable({ 
  providedIn: 'root' 
}) 
export class AttendanceService { 
  private http = inject(HttpClient); 
  private apiUrl = 'http://localhost:8080/api/attendance'; 
 
  markAttendance(type: 'IN' | 'OUT') { 
    // Enviamos el parametro como query param según tu backend controller 
    return this.http.post(`${this.apiUrl}/mark?type=${type}`, {}); 
  } 
}