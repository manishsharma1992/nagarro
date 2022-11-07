import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/rest/auth/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public isLoggedIn: boolean = false;
  constructor(private httpClient: HttpClient) { }

  authenticateLogin(request: any): Observable<any> {
    return this.httpClient.post(AUTH_API + 'authenticate', request, httpOptions);
  }

}
