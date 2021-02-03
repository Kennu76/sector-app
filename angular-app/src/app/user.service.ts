import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { User } from "./model/user";

@Injectable()
export class UserService {
  private base = "http://localhost:8080";
  private usersUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = this.base + "/users/save";
  }

  public findAll(): Observable<any> {
    return this.http.get<any>(this.usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this.usersUrl, user);
  }
}
