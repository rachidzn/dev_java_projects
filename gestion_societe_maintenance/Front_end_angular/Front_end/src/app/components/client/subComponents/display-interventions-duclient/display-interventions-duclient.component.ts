import { Component, OnInit } from '@angular/core';
import { Intervention } from '../../../../metier/objet-intervention';
import { Observable, Subject, Subscription } from 'rxjs';
import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Intervenant } from '../../../../metier/objet-intervenant';
import { Materiel } from '../../../../metier/objet-materiel';
import { MaterielRepositoryService } from '../../../../services/materiel-repository.service';
import { IntervenantRepositoryService } from '../../../../services/intervenant-repository.service';
import { ClientRepositoryService } from '../../../../services/client-repository.service';
import { Client } from '../../../../metier/objet-client';
import { NgxSmartModalService } from 'ngx-smart-modal';

@Component({
  selector: 'app-display-interventions-duclient',
  templateUrl: './display-interventions-duclient.component.html',
  styleUrls: ['./display-interventions-duclient.component.css']
})
export class DisplayInterventionsDuclientComponent implements OnInit {
  public interventionsSubject : Subject<Intervention[]>
  private interventionsSouscription : Subscription;
  public intervenantsSubject : Subject<Intervenant[]>
  private intervenantsSouscription : Subscription;
  public materielsSubject : Subject<Materiel[]>
  private materielsSouscription : Subscription;

  public curDate=new Date();
  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
  public currentIntervention : Intervention;
  public editIdIntervention: number;
  
  public currentIntervenant : Intervenant;
  public currentMateriel : Materiel;

  public Clientconnected: Client=new Client(0,"","",0);

  constructor(public ngxSmartModalService: NgxSmartModalService,private clientRepository: ClientRepositoryService, private interventionRepository: InterventionsRepositoryService,private materielRepository: MaterielRepositoryService,private intervenantnRepository: IntervenantRepositoryService) {
    //pour le ngfor
    this.interventionsSubject= new Subject<Intervention[]>();
    this.intervenantsSubject= new Subject<Intervenant[]>();
    this.materielsSubject= new Subject<Materiel[]>();

   }


public pageChanged(event){
  this.interventionRepository.setnopage(event.page-1);
}
  ngOnInit() {
    /*this.interventionsSouscription=this.interventionRepository.getInterventionsAsObservable().subscribe(p=>{
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
      
    }); */

    {
      this.interventionsSouscription=this.interventionRepository.findclientById(this.clientRepository.setclient_toconnect()).subscribe(
        // je reçois les nouvelles pages d'Interventions
        interventions=> { this.interventionsSubject.next(interventions);
      });
     // this.intervenantsSouscription=this.intervenantnRepository.getIntervenantsAsObservable2().subscribe(p=>{
        // je reçois les nouvelles pages d'Interventions
       // this.intervenantsSubject.next(p.content);
        
     // });
      this.clientRepository.findById(
       this.clientRepository.setclient_toconnect()).subscribe(Client=> { this.Clientconnected=Client;
     },);
     this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    this.interventionRepository.refreshListe();
    this.intervenantnRepository.refreshListe();
    this.materielRepository.refreshListe();
    this.clientRepository.refreshListe();
  

  }


  }

public genererpdf(id:number){
  this.interventionRepository.findById(id).subscribe(Intervention=> { this.currentIntervention=Intervention;
  });
}


}
