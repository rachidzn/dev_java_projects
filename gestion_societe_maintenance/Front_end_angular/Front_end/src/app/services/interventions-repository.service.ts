import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from "rxjs";
import { Intervention } from '../metier/objet-intervention';
import { HttpClient } from '@angular/common/http';
import { Page } from '../metier/page';
import { Evenement } from '../metier/objet-event';


@Injectable({
  providedIn: 'root'
})
export class InterventionsRepositoryService {

  public listinter:Observable< Intervention[]>;
  public listevents:Observable< [{}]>;

  private interventionsSubject: BehaviorSubject<Page<Intervention>>;
  
  private noPage : number;
  private taillePage : number;

  //injection du client
  constructor(private http: HttpClient){
    this.interventionsSubject= new BehaviorSubject<Page<Intervention>>(Page.emptyPage<Intervention>());
    this.noPage=0;
    this.taillePage=9;

  }

public getInterventionsAsObservable(): Observable <Page<Intervention>> {
  return this.interventionsSubject.asObservable();
}

  
public getEvents(): Observable <Evenement[]> {
  return this.http.get<Evenement[]>(`http://localhost:8080/loncogroup-c/interventions/evenement`);
}
 
 
public getEventsbyIntervenant(id:number): Observable <Evenement[]> {
  return this.http.get<Evenement[]>(`http://localhost:8080/loncogroup-c/interventions/evenement/intervenant/${id}`);
}


   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Intervention>>(
     `http://localhost:8080//loncogroup-c/interventions?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.interventionsSubject.next(p);
    });
    
   }



   public findintervenantById(id:number):Observable<Intervention[]>{
    return this.http.get<Intervention[]>(`http://localhost:8080/loncogroup-c/interventions/intervenant/${id}`);
  }

  public findMaterielById(id:number):Observable<Intervention[]>{
    return this.http.get<Intervention[]>(`http://localhost:8080/loncogroup-c/interventions/materiels/${id}`);
  }

public findclientById(id:number):Observable<Intervention[]>{
    return this.http.get<Intervention[]>(`http://localhost:8080/loncogroup-c/interventions/client/${id}`);
  }

   public findById(id:number):Observable<Intervention>{
    return this.http.get<Intervention>(`http://localhost:8080/loncogroup-c/interventions/${id}`);


  }

  public removeIntervention(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/interventions/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateIntervention(intervention : Intervention): void {
    this.http.put(`http://localhost:8080/loncogroup-c/interventions/save/`, intervention.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createIntervention(intervention : Intervention): void {
    this.http.post(`http://localhost:8080/loncogroup-c/interventions/save/`, intervention.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createIntervention2(intervention : Intervention): void {
    this.http.post(`http://localhost:8080/loncogroup-c/interventions/save/`, intervention.toJson()).subscribe(resp =>{
             
  });
  }




}



