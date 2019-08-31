import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

  private url:string = "http://localhost:8081/ionic";

  constructor(public http: HttpClient) { }

  public getData(): Observable<Object>{
    return this.http.get(this.url, {responseType: 'text'});
  }

  public putData(): Observable<Object> {
    return this.http.put(this.url, {}, {responseType: 'text'});
  }
}
