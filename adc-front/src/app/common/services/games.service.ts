import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { HttpHeaders } from '@angular/common/http';
//import { HttpErrorHandler, HandleError } from '../http-error-handler.service';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Game } from '../data/game';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GamesService {

  url = "./archiduchess/onlineGames";

  public getGames (): Observable<Game[]> {
    return this.http.get<Game[]>(this.url)
  }

  public getFens (id : number) : Observable<string[]> {

    let urlFen : string = this.url+"/"+id+"/fens";
    console.log("urlFen --> "+ urlFen)
    return this.http.get<string[]>(urlFen)
  }

  public getSans (id : number) : Observable<string[]> {

    let urlSan : string = this.url+"/"+id+"/sans";
    console.log("urlSan --> "+ urlSan)
    return this.http.get<string[]>(urlSan)
  }

    // this.http.get('https://api.github.com/users/didier-tp)    
    // .subscribe(      
    //   data => {  console.log(data);  } , 
    //  (err: HttpErrorResponse) => 
    //         {        
    //           if (err.error instanceof Error) 
    //           {          
    //             console.log("Client-side error occured."); 
    //          } 
    //          else 
    //          {          
    //               console.log("Server-side error occured.");
    //         }   
    // }) ;

  constructor(private http : HttpClient) { }
}
