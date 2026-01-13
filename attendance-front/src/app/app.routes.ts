import { Routes } from '@angular/router'; 
import { LoginComponent } from './pages/login/login'; 
import { AttendanceComponent } from './pages/attendance/attendance'; 
 
export const routes: Routes = [ 
  { path: 'login', component: LoginComponent }, 
  { path: 'attendance', component: AttendanceComponent }, 
  { path: '', redirectTo: 'login', pathMatch: 'full' } 
];