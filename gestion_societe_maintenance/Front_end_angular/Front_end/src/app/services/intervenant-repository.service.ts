import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Intervenant } from '../metier/objet-intervenant';
import { HttpClient } from '@angular/common/http';
import { Page } from '../metier/page';


@Injectable({
  providedIn: 'root'
})
export class IntervenantRepositoryService {
 

  private intervenantsSubject: BehaviorSubject<Page<Intervenant>>;

  private noPage : number;
  private taillePage : number;


  //injection du client
  constructor(private http: HttpClient){
    this.intervenantsSubject= new BehaviorSubject<Page<Intervenant>>(Page.emptyPage<Intervenant>());
    this.noPage=0;
    this.taillePage=3;

  }

public getIntervenantsAsObservable(): Observable <Page<Intervenant>> {
  this.taillePage=3;
  return this.intervenantsSubject.asObservable();
}

public getIntervenantsAsObservable2(): Observable <Page<Intervenant>> {
  this.taillePage=1000;
  return this.intervenantsSubject.asObservable();
}
 



   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Intervenant>>(
     `http://localhost:8080//loncogroup-c/intervenants?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.intervenantsSubject.next(p);
    });
    
   }




   public findById(id:number):Observable<Intervenant>{
    return this.http.get<Intervenant>(`http://localhost:8080/loncogroup-c/intervenants/${id}`);


  }

  public removeIntervenant(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/intervenants/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateIntervenant(intervenant : Intervenant): void {
    this.http.put(`http://localhost:8080/loncogroup-c/intervenants/save/`, intervenant.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createIntervenant(intervenant : Intervenant): void {
    this.http.post(`http://localhost:8080/loncogroup-c/intervenants/save/`, intervenant.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }



  private idconnected:number=0;

public getintervenant_toconnect(id:number){
  this.idconnected=id;
}






public setintervenant_toconnect(){
  return this.idconnected;
}
setclient_toconnect() {
 return this.idconnected;
}
}
