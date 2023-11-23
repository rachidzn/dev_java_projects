import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Client } from '../metier/objet-client';

@Injectable({
  providedIn: 'root'
})
export class ClientRepositoryService {
  private clientsSubject : BehaviorSubject<Page<Client>>;
  private noPage : number;
  private taillePage : number;
private idconnected : number=0;

  constructor(private http: HttpClient) {
    //on demarre avec une page vide 
    this.clientsSubject = new BehaviorSubject<Page<Client>>(Page.emptyPage<Client>());
    this.noPage=0;
    this.taillePage=3;

   }
   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Client>>(
     `http://localhost:8080//loncogroup-c/clients?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.clientsSubject.next(p);
    });
    
   }



   public getClientspageAsObservable2(): Observable <Page<Client>>{
   return this.http.get<Page<Client>>(
      `http://localhost:8080//loncogroup-c/clients?page=${this.noPage}&size=${100}`); //1000
     

   }


   public getClientAsObservable(): Observable <Page<Client>> {
    return this.clientsSubject.asObservable();
  }
  

   public findById(id:number):Observable<Client>{
    return this.http.get<Client>(`http://localhost:8080/loncogroup-c/clients/${id}`);


  }

  public removeClient(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/clients/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateClient(client : Client): void {
    this.http.put(`http://localhost:8080/loncogroup-c/clients/save/`, client.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createClient(client : Client): void {
    this.http.post(`http://localhost:8080/loncogroup-c/clients/save/`, client.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }




  public getclient_toconnect(id:number){
    this.idconnected=id;
  }
  
  public setclient_toconnect():number{
    return this.idconnected;
  }

}
