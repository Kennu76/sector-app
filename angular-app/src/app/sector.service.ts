import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs/internal/Observable";
import { Sector } from "./model/sector";

@Injectable()
export class SectorService {
  private base = "http://localhost:8080";
  private sectorsUrl: string;

  constructor(private http: HttpClient) {
    this.sectorsUrl = this.base + "/sectors";
  }

  public findAll(): Observable<any> {
    return this.http.get<any>(this.sectorsUrl);
  }


}
