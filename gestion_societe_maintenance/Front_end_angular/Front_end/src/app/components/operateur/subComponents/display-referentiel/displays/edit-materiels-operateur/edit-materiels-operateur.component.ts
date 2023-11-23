import { Component, OnInit ,Input,OnChanges} from '@angular/core';
import { Materiel } from '../../../../../../metier/objet-materiel';

import { MaterielRepositoryService } from '../../../../../../services/materiel-repository.service';
import { Site } from '../../../../../../metier/objet-site';
import { Article } from '../../../../../../metier/objet-article';
import { Intervention } from '../../../../../../metier/objet-intervention';
import { InterventionsRepositoryService } from '../../../../../../services/interventions-repository.service';
import { Client } from '../../../../../../metier/objet-client';
import { Subject, Subscription } from 'rxjs';
import { ClientRepositoryService } from '../../../../../../services/client-repository.service';
import { ArticleRepositoryService } from '../../../../../../services/article-repositories.service';
import { SiteRepositoryService } from '../../../../../../services/site-repository.service';

@Component({
  selector: 'app-edit-materiels-operateur',
  templateUrl: './edit-materiels-operateur.component.html',
  styleUrls: ['./edit-materiels-operateur.component.css']
})
export class EditMaterielsOperateurComponent implements OnInit,OnChanges {

  @Input() public editIdMateriel: number;
  public currentMateriel : Materiel;
  public currentsite : Site;
  public currentarticle : Article;
  public currentClient : Client;
  public interventionsmateriels: Intervention[];

  public clientsSubject : Subject<Client[]>
  private clientSouscription : Subscription;
  public sitesSubject : Subject<Site[]>
  private siteSouscription : Subscription;
  public articlesSubject : Subject<Article[]>
  private articleSouscription : Subscription;

  constructor(private MaterielRepository : MaterielRepositoryService,private InterventiontRepository : InterventionsRepositoryService,private ClientRepository: ClientRepositoryService, private ArticleRepository : ArticleRepositoryService,private SiteRepository : SiteRepositoryService) {
  this.clientsSubject=new Subject<Client[]>();
  this.sitesSubject=new Subject<Site[]>();
  this.articlesSubject=new Subject<Article[]>();

   }



   public saveMateriel() {
    
      if (this.currentMateriel.id > 0){
        let InterToSave = new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentMateriel);

        console.log(InterToSave);
        this.MaterielRepository.updateMateriel(InterToSave);
        this.currentMateriel = new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
        this.interventionsmateriels=[];
      }
      else{
        let InterToSave = new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentMateriel);

        console.log(InterToSave);
        this.MaterielRepository.createMateriel(InterToSave);
        this.currentMateriel = new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
        this.interventionsmateriels=[];
      }
 
    }


  public cancelMateriel() {
    this.currentMateriel = new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
    this.interventionsmateriels=[];
  }

public removeMateriel(){

  this.MaterielRepository.removeMateriel(this.currentMateriel.id);
  this.currentMateriel=new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
  this.interventionsmateriels=[];
}



  ngOnInit() {
    this.currentMateriel=new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
    this.interventionsmateriels=[];

    this.ClientRepository.getClientspageAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.clientsSubject.next(p.content);
    });
    
    this.ArticleRepository.getArticlespageAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.articlesSubject.next(p.content);
    });
    this.SiteRepository.getSiteAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.sitesSubject.next(p.content);
    });


    this.SiteRepository.refreshListe();
   
    this.ArticleRepository.refreshListe();

  }

  ngOnChanges(changes:any){
    
    if (this.editIdMateriel==0){
      this.currentMateriel=new Materiel(0,"",this.currentsite,this.currentClient,this.currentarticle);
      this.interventionsmateriels=[];
    }
    else{
      
      this.MaterielRepository.findById(this.editIdMateriel).subscribe(Materiel=> { this.currentMateriel=Materiel;
      });

      
    this.InterventiontRepository.findMaterielById(this.editIdMateriel).subscribe(Materiels=> { this.interventionsmateriels=Materiels;
    });console.log(this.interventionsmateriels);

    




    }
  }

 


 
    }





 

     


