 
import { HttpInterceptorFn, HttpErrorResponse } from '@angular/common/http'; 
import { inject } from '@angular/core'; 
import { AuthService } from '../services/auth.service'; 
import { catchError, switchMap, throwError } from 'rxjs'; 

export const authInterceptor: HttpInterceptorFn = (req, next) => { 
  const authService = inject(AuthService); 
  const token = authService.getToken(); 
  const refreshToken = authService.getRefreshToken(); // <--- Ahora ya existe

  if (req.url.includes('/api/auth/')) { 
    return next(req); 
  }

  let authReq = req; 
  if (token) { 
    authReq = req.clone({ setHeaders: { Authorization: `Bearer ${token}` } }); 
  }

  return next(authReq).pipe( 
    catchError((error: HttpErrorResponse) => { 
      // Si el error es de auth Y tenemos con qué intentar recuperarnos
      if ((error.status === 401 || error.status === 403) && refreshToken) { 
        return authService.refreshToken().pipe( 
          switchMap((newTokens) => { 
            const newAuthReq = req.clone({ 
              setHeaders: { Authorization: `Bearer ${newTokens.accessToken}` } 
            }); 
            return next(newAuthReq); 
          }), 
          catchError((refreshErr) => { 
            authService.logout(); 
            return throwError(() => refreshErr); 
          }) 
        ); 
      } 

      // Si no hay refresh token o el error es de auth, logout inmediato
      if (error.status === 401 || error.status === 403) {
        authService.logout();
      }
      
      return throwError(() => error); 
    }) 
  ); 
};