import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { Page } from '../metier/page';
import { Article } from '../metier/objet-article';

@Injectable({
  providedIn: 'root'
})
export class ArticleRepositoryService {
  private articlesSubject : BehaviorSubject<Page<Article>>;
  private noPage : number;
  private taillePage : number;


  constructor(private http: HttpClient) {
    //on demarre avec une page vide 
    this.articlesSubject = new BehaviorSubject<Page<Article>>(Page.emptyPage<Article>());
    this.noPage=0;
    this.taillePage=5;

   }
   public setnopage(no:number){
     this.noPage=no;
     this.refreshListe();
   }

   public refreshListe():void{
     this.http.get<Page<Article>>(
     `http://localhost:8080//loncogroup-c/articles?page=${this.noPage}&size=${this.taillePage}`)
     .subscribe(p => {this.articlesSubject.next(p);
    });
    
   }

   public getArticlespageAsObservable(): Observable<Page<Article>>{
     return this.articlesSubject.asObservable();
   }
   public getArticlespageAsObservable2(): Observable<Page<Article>>{
    this.taillePage=1000;
    return this.articlesSubject.asObservable();
  }


   public findById(id:number):Observable<Article>{
    return this.http.get<Article>(`http://localhost:8080/loncogroup-c/articles/${id}`);


  }

  public removeArticle(id:number):void{
    this.http.delete(`http://localhost:8080/loncogroup-c/articles/remove/${id}`)
                          .subscribe(resp => {this.refreshListe();
                          });


  }
  public updateArticle(article : Article): void {
    this.http.put(`http://localhost:8080/loncogroup-c/articles/save/`, article.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

  public createArticle(article : Article): void {
    this.http.post(`http://localhost:8080/loncogroup-c/articles/save/`, article.toJson()).subscribe(resp =>{
              this.refreshListe();
    });
  }

}
