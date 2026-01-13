import { HttpInterceptorFn } from '@angular/common/http'; 
import { finalize } from 'rxjs'; 
 
// Podrías conectar esto a un servicio de UI global 
export const loadingInterceptor: HttpInterceptorFn = (req, next) => { 
  console.log('Loading started...'); // Aquí activarías tu spinner 
  return next(req).pipe( 
    finalize(() => console.log('Loading finished...')) // Aquí desactivarías tu spinner 
  ); 
}; 