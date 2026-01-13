import { Component, inject } from '@angular/core'; 
import { CommonModule } from '@angular/common'; 
import { AttendanceService } from '../../services/attendance.service'; 
import { AuthService } from '../../services/auth.service'; 
 
@Component({ 
  selector: 'app-attendance', 
  standalone: true, 
  imports: [CommonModule], 
  templateUrl: './attendance.html' 
}) 
export class AttendanceComponent { 
  message = ''; 
  private attendanceService = inject(AttendanceService); 
  private authService = inject(AuthService); 
 
  mark(type: 'IN' | 'OUT') { 
    this.attendanceService.markAttendance(type).subscribe({ 
      next: (res: any) => { 
        // Asumiendo que el backend devuelve un string simple o json 
        this.message = `Success: Marked ${type} at ${new Date().toLocaleTimeString()}`; 
      },
      error: (err) => { 
        this.message = 'Error marking attendance.'; 
      } 
    }); 
  } 
 
  logout() { 
    this.authService.logout(); 
  } 
} 