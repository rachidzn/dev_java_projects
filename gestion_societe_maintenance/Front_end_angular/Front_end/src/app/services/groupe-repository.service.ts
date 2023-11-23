import { Injectable } from '@angular/core';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Groupe } from '../metier/objet-groupe';
import { Page } from '../metier/page';

@Injectable({
  providedIn: 'root'
})
export class GroupeRepositoryService {

  
  private groupesSubject: BehaviorSubject<Page<Groupe>>;
  
  private noPage : number;
  private taillePage : number;

  //injection du client
  constructor(private http: HttpClient){
    this.groupesSubject= new BehaviorSubject<Page<Groupe>>(Page.emptyPage<Groupe>());
    this.noPage=0;
    this.taillePage=10;

  }


  public findintervenantById(id:number):Observable<Groupe[]>{
    return this.http.get<Groupe[]>(`http://localhost:8080/loncogroup-c/groupeintervenants/intervenant/${id}`);


  }



public getGroupeAsObservable(): Observable <Page<Groupe>> {
  return this.groupesSubject.asObservable();
}



   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Groupe>>(
     `http://localhost:8080//loncogroup-c/groupeintervenants?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.groupesSubject.next(p);
    });
    
   }




   public findById(id:number):Observable<Groupe>{
    return this.http.get<Groupe>(`http://localhost:8080/loncogroup-c/groupeintervenants/${id}`);


  }

  public removeGroupe(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/groupeintervenants/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateGroupe(groupe : Groupe): void {
    this.http.put(`http://localhost:8080/loncogroup-c/groupeintervenants/save/`, groupe.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createGroupe(groupe : Groupe): void {
    this.http.post(`http://localhost:8080/loncogroup-c/groupeintervenants/save/`, groupe.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }
 

}
