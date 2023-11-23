import { Component, OnInit } from '@angular/core';
import { Intervention } from '../../../../metier/objet-intervention';
import { Observable, Subject, Subscription } from 'rxjs';
import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Intervenant } from '../../../../metier/objet-intervenant';
import { Materiel } from '../../../../metier/objet-materiel';
import { IntervenantRepositoryService } from '../../../../services/intervenant-repository.service';
import { MaterielRepositoryService } from '../../../../services/materiel-repository.service';

@Component({
  selector: 'app-display-interventions',
  templateUrl: './display-interventions.component.html',
  styleUrls: ['./display-interventions.component.css']
})
export class DisplayInterventionsComponent implements OnInit {

  public interventionsSubject : Subject<Intervention[]>
  private interventionsSouscription : Subscription;
  public intervenantsSubject : Subject<Intervenant[]>
  private intervenantsSouscription : Subscription;
  public materielsSubject : Subject<Materiel[]>
  private materielsSouscription : Subscription;
 


  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
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
    this.interventionsSouscription=this.interventionRepository.findintervenantById(this.intervenantnRepository.setintervenant_toconnect()).subscribe(
      // je reçois les nouvelles pages d'Interventions
      interventions=> { this.interventionsSubject.next(interventions);
    });
   // this.intervenantsSouscription=this.intervenantnRepository.getIntervenantsAsObservable2().subscribe(p=>{
      // je reçois les nouvelles pages d'Interventions
     // this.intervenantsSubject.next(p.content);
      
   // });
    this.intervenantnRepository.findById(
     this.intervenantnRepository.setintervenant_toconnect()).subscribe(Intervenant=> { this.Intervenantconnected=Intervenant;
   },);



    this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    this.interventionRepository.refreshListe();
    this.intervenantnRepository.refreshListe();
    this.materielRepository.refreshListe();
  }

  public details(id: number) {
    this.editIdIntervention=id;
    this.interventionRepository.findById(this.editIdIntervention).subscribe(Intervention=> { this.currentIntervention=Intervention;
    });
  }

  

  public saveIntervention() {
    
    if (this.currentIntervention.id > 0){
      let InterToSave = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
      
      InterToSave.copyFrom(this.currentIntervention);

      console.log(InterToSave);
      this.interventionRepository.updateIntervention(InterToSave);
      this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    }
    else{
      let InterToSave = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
      
     
      InterToSave.copyFrom(this.currentIntervention);

      console.log(InterToSave);
      this.interventionRepository.createIntervention(InterToSave);
      this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    }

  }


public cancelIntervention() {
  this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
}

public removeIntervention(){

this.interventionRepository.removeIntervention(this.currentIntervention.id);
this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
}




}