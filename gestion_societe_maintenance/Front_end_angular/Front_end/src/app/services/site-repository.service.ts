import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from "rxjs";
import { Site } from '../metier/objet-site';
import { HttpClient } from '@angular/common/http';
import { Page } from '../metier/page';

@Injectable({
  providedIn: 'root'
})
export class SiteRepositoryService {

  private sitesSubject: BehaviorSubject<Page<Site>>;
  
  private noPage : number;
  private taillePage : number;

  //injection du client
  constructor(private http: HttpClient){
    this.sitesSubject= new BehaviorSubject<Page<Site>>(Page.emptyPage<Site>());
    this.noPage=0;
    this.taillePage=3;

  }

public getSitesAsObservable(): Observable <Page<Site>> {
  return this.sitesSubject.asObservable();
}

  
public getSiteAsObservable2(): Observable <Page<Site>> {
  return this.http.get<Page<Site>>(
    `http://localhost:8080//loncogroup-c/sites?page=${this.noPage}&size=${1000}`)
    
   }


 



   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Site>>(
     `http://localhost:8080//loncogroup-c/sites?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.sitesSubject.next(p);
    });
    
   }




   public findById(id:number):Observable<Site>{
    return this.http.get<Site>(`http://localhost:8080/loncogroup-c/sites/${id}`);


  }

  public removeSite(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/sites/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateSite(site : Site): void {
    this.http.put(`http://localhost:8080/loncogroup-c/sites/save/`, site.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createSite(site : Site): void {
    this.http.post(`http://localhost:8080/loncogroup-c/sites/save/`, site.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }
}
