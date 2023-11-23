import { Component, OnInit} from '@angular/core';
import { Intervention } from '../../../../metier/objet-intervention';

import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Page } from '../../../../metier/page';
import { Subject, Subscription } from 'rxjs';

import { Intervenant } from '../../../../metier/objet-intervenant';
import { IntervenantRepositoryService } from '../../../../services/intervenant-repository.service';

import { Materiel } from '../../../../metier/objet-materiel';
import { MaterielRepositoryService } from '../../../../services/materiel-repository.service';
import { Site } from '../../../../metier/objet-site';
import { Client } from '../../../../metier/objet-client';
import { Article } from '../../../../metier/objet-article';

@Component({
  selector: 'app-display-interventions-operateur',
  templateUrl: './display-interventions-operateur.component.html',
  styleUrls: ['./display-interventions-operateur.component.css']
})
export class DisplayInterventionsOperateurComponent implements OnInit{

  curDate=new Date();

  public interventionsSubject : Subject<Intervention[]>
  private interventionsSouscription : Subscription;
  public intervenantsSubject : Subject<Intervenant[]>
  private intervenantsSouscription : Subscription;
  public materielsSubject : Subject<Materiel[]>
  private materielsSouscription : Subscription;
 


  public totalItems:number=0;
  public currentPage : number=1;
  public taillePage : number=10;
  public currentIntervention : Intervention;
  public editIdIntervention: number;
  
  
  public currentIntervenant : Intervenant;
  public currentMateriel : Materiel;
  public Intervenantconnected: Intervenant=new Intervenant(0,"","","","purple");

  constructor(private interventionRepository: InterventionsRepositoryService,private materielRepository: MaterielRepositoryService,private intervenantnRepository: IntervenantRepositoryService) {
    //pour le ngfor
    this.interventionsSubject= new Subject<Intervention[]>();
    this.intervenantsSubject= new Subject<Intervenant[]>();
    this.materielsSubject= new Subject<Materiel[]>();

   }


public pageChanged(event){
  this.interventionRepository.setnopage(event.page-1);
}
  ngOnInit() {
    this.interventionsSouscription=this.interventionRepository.getInterventionsAsObservable().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.interventionsSubject.next(p.content);
      this.totalItems=p.totalElements;
      this.currentPage=p.number+1;
      this.taillePage=p.size;
    });
    
    this.intervenantsSouscription=this.intervenantnRepository.getIntervenantsAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.intervenantsSubject.next(p.content);
      
    });
    this.materielsSouscription=this.materielRepository.getMaterielsAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
      this.materielsSubject.next(p.content);
      
    });



    this. currentIntervenant =new Intervenant(0,"","","","purple");
   this. currentMateriel=new Materiel(0,"",new Site(0,"","",0),new Client(0,"","",0),new Article(0,"",""));
    this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");

    
    this.interventionRepository.refreshListe();
    this.intervenantnRepository.refreshListe();
    this.materielRepository.refreshListe();
  }

  public details(id: number) {
    
    this.interventionRepository.findById(id).subscribe(Intervention=> { this.currentIntervention=Intervention;
    });
  }



  public envoyerFormulaire(id: number){
    

    this.interventionRepository.findById(id).subscribe(Intervention=> { this.currentIntervention=Intervention;
    });
    this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
  }

  

  public saveIntervention() {
    
    if (this.currentIntervention.id > 0){
      let InterToSave = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
      
      // retransformation du modele/json du formulaire
      // en veritable objet Livre avec ses méthodes
      InterToSave.copyFrom(this.currentIntervention);
      if (InterToSave.statut=="En Attente"){
        InterToSave.color='red'
      }
      if (InterToSave.statut=="Reprogrammée"){
        InterToSave.color='orange'
      }
      if (InterToSave.statut=="Terminée"){
        InterToSave.color='green'
      }
      console.log(InterToSave);
      this.interventionRepository.updateIntervention(InterToSave);
      this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    }
    else{
      let InterToSave = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
      
      // retransformation du modele/json du formulaire
      // en veritable objet Livre avec ses méthodes
      InterToSave.copyFrom(this.currentIntervention);
      InterToSave.title="Intervention de "+this.currentIntervention.intervenant.prenom+this.currentIntervention.intervenant.nom+" sur "+this.currentIntervention.materiel.article.marque+"- Site : "+this.currentIntervention.materiel.lesite.nom;
      if (InterToSave.statut=="En Attente"){
        InterToSave.color='red'
      }
      if (InterToSave.statut=="Reprogrammée"){
        InterToSave.color='orange'
      }
      if (InterToSave.statut=="Terminée"){
        InterToSave.color='green'
      }
      
      console.log(InterToSave);
      this.interventionRepository.createIntervention(InterToSave);
      this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    }

  }


public cancelIntervention() {
  this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
  this. currentIntervenant =new Intervenant(0,"","","","purple");
  this. currentMateriel=new Materiel(0,"",new Site(0,"","",0),new Client(0,"","",0),new Article(0,"",""));
}

public removeIntervention(){

this.interventionRepository.removeIntervention(this.currentIntervention.id);
this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
}


public generer_intervention_preventive(){
 

}

}







