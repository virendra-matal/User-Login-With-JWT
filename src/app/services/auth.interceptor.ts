import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginService } from './login.service';

const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private login:LoginService) {}


  
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {

    let newRequest=request;
    const token=this.login.getToken();

    console.log('Interceptor: ',token);
    

    if (token!=null) {
      // newRequest=newRequest.clone({setHeaders:{Authorization:`Bearer ${token}`}})
      // newRequest=newRequest.clone({setHeaders:{Authorization:`Bearer ${token}`}})

      // newRequest=request.clone({headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer '+token)});
// console.log("Token Vallue:"+token);
      newRequest = request.clone({ 
        headers: request.headers.set('Authorization', `Bearer ${token}`),
      });

      // console.log("abc",newRequest);
      
      // console.log('check in interceptor...');
      
    }

    return next.handle(newRequest);
  }
}
export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
