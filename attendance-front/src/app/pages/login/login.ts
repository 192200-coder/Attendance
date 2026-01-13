import { Component, inject } from '@angular/core'; 
import { CommonModule } from '@angular/common'; 
import { FormsModule } from '@angular/forms'; 
import { Router } from '@angular/router'; 
import { AuthService } from '../../services/auth.service'; 

@Component({ 
  selector: 'app-login', 
  standalone: true, 
  imports: [CommonModule, FormsModule], // Importar FormsModule para ngModel 
  templateUrl: './login.html', 
  styleUrls: ['./login.css'] 
}) 
export class LoginComponent { 
  credentials = { username: '', password: '' }; 
  isLoading = false; 
  errorMsg = ''; 
 
  private authService = inject(AuthService); 
  private router = inject(Router); 
 
  onLogin() { 
    this.isLoading = true; 
    this.authService.login(this.credentials).subscribe({ 
      next: () => { 
        this.router.navigate(['/attendance']); 
      }, 
      error: (err) => { 
        this.errorMsg = 'Invalid credentials'; 
        this.isLoading = false; 
      } 
    }); 
  } 
} 