import { Component, OnInit } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { Materiel } from '../../../../metier/objet-materiel';
import { Client } from '../../../../metier/objet-client';
import { ClientRepositoryService } from '../../../../services/client-repository.service';
import { MaterielRepositoryService } from '../../../../services/materiel-repository.service';
import { Site } from '../../../../metier/objet-site';
import { Article } from '../../../../metier/objet-article';
import { ArticleRepositoryService } from '../../../../services/article-repositories.service';
import { SiteRepositoryService } from '../../../../services/site-repository.service';

@Component({
  selector: 'app-display-tableau-client',
  templateUrl: './display-tableau-client.component.html',
  styleUrls: ['./display-tableau-client.component.css']
})
export class DisplayTableauClientComponent implements OnInit {
  public clientsSubject : Subject<Client[]>
  private clientSouscription : Subscription;
  public materielSubject : Subject<Materiel[]>
  private materielsSouscription : Subscription;
  public sitesSubject : Subject<Site[]>
  private siteSouscription : Subscription;
  public articlesSubject : Subject<Article[]>
  private articleSouscription : Subscription;
  
  public materielclient : Materiel[];
  public Clientconnected: Client=new Client(0,"","",0);
  
  
  currentClient: Client;
  currentMateriel: Materiel;
  currentsite: Site;
  currentarticle: Article;
  
  constructor(private clientRepository: ClientRepositoryService, 
    private materielRepository: MaterielRepositoryService,private articleRepository: ArticleRepositoryService, private siteRepository: SiteRepositoryService) {
    //pour le ngfor
    this.materielSubject= new Subject<Materiel[]>();

   }


public pageChanged(event){
  this.materielRepository.setnopage(event.page-1);
}
  ngOnInit() {
  
    this.currentMateriel=new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
   // this.materiels=[];

     // this.interventionsSouscription=this.interventionRepository.findclientById(this.clientRepository.setclient_toconnect()).subscribe(
        // je reçois les nouvelles pages d'Interventions
      //  interventions=> { this.interventionsSubject.next(interventions);
      //});
     // this.intervenantsSouscription=this.intervenantnRepository.getIntervenantsAsObservable2().subscribe(p=>{
        // je reçois les nouvelles pages d'Interventions
       // this.intervenantsSubject.next(p.content);
        
     // });

    

      this.clientRepository.findById(
       this.clientRepository.setclient_toconnect()).subscribe(Client=> { this.Clientconnected=Client;
     },);

     this.materielRepository.findclientById(this.clientRepository.setclient_toconnect()).subscribe(Materiels=> { this.materielclient=Materiels;
     });

    this.materielRepository.refreshListe();
    this.siteRepository.refreshListe();
   
    this.articleRepository.refreshListe();


  }



}
