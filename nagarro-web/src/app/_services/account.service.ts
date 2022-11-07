import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/rest/api/';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) { }

  fetchAllAccounts(): Observable<any> {
    return this.http.get(AUTH_API + 'fetch-all-accounts', httpOptions);
  }

  fetchStatementsByFilter(request: any): Observable<any> {

    return this.http.post(AUTH_API + 'fetchByFilterParams', request, httpOptions);
  }
}
