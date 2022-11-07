import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpErrorResponse
} from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';

const TOKEN_HEADER_KEY = 'Authorization';   

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  private isRefreshing = false;
  constructor(private tokenService: TokenStorageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    let authReq = request;
    const token = this.tokenService.getToken();
    if (token != null) {
      authReq = request.clone({ headers: request.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(request).pipe(catchError((error: any) => {
      console.log(error);
      if (error instanceof HttpErrorResponse) {
        console.log(error.status);
        console.log(error.statusText);
        if (error.status === 401) {
          window.location.href = "/login";
        }
      }
      throw throwError(() => new Error(error));
    }) as any);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
