import { ApplicationConfig } from '@angular/core'; 
import { provideRouter } from '@angular/router'; 
import { provideHttpClient, withInterceptors } from '@angular/common/http'; 
 
import { routes } from './app.routes'; 
import { authInterceptor } from './interceptors/auth.interceptor'; 
import { loadingInterceptor } from './interceptors/loading.interceptor'; 
 
export const appConfig: ApplicationConfig = { 
  providers: [ 
    provideRouter(routes), 
    // Configuración moderna de HTTP con Interceptores Funcionales 
    provideHttpClient( 
      withInterceptors([loadingInterceptor, authInterceptor])  
      // El orden importa: primero loading, luego auth 
    ) 
  ]
};