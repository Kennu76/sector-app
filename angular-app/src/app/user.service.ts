import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { User } from "./model/user";

@Injectable()
export class UserService {
  private base = "http://localhost:8080";
  private usersUrl: string;
  private saveUserUrl: string;

  constructor(private http: HttpClient) {
    this.usersUrl = this.base + "/users";
    this.saveUserUrl = this.usersUrl + "/save"
  }

  public findAll(): Observable<User[]> {
    console.log(this.http.get<User[]>(this.usersUrl));
    return this.http.get<User[]>(this.usersUrl);
  }

  public save(user: User) {
    return this.http.post<User>(this.saveUserUrl, user);
  }

}
