import { Component, OnInit, Input} from '@angular/core';

import { NgxSmartModalService } from 'ngx-smart-modal';
import { Subject, Subscription } from 'rxjs';

import { Intervenant } from '../../metier/objet-intervenant';
import { IntervenantRepositoryService } from '../../services/intervenant-repository.service';
import { Client } from '../../metier/objet-client';
import { ClientRepositoryService } from '../../services/client-repository.service';



@Component({
  selector: 'app-vitrine',
  templateUrl: './vitrine.component.html',
  styleUrls: ['./vitrine.component.css']
})
export class VitrineComponent implements OnInit {
public currentintervenant: Intervenant=new Intervenant(0,"","","","purple");
public currentclient: Client=new Client(0,"","",0);

public intervenantsSubject : Subject<Intervenant[]>
  private intervenantsSouscription : Subscription;
  public clientsSubject : Subject<Client[]>
  private clientsSouscription : Subscription;


  constructor(public ngxSmartModalService: NgxSmartModalService,private intervenantnRepository: IntervenantRepositoryService,private clientRepository: ClientRepositoryService) {
    this.intervenantsSubject= new Subject<Intervenant[]>();
    this.clientsSubject= new Subject<Client[]>();
  }

  
  ngOnInit(){
    this.intervenantsSouscription=this.intervenantnRepository.getIntervenantsAsObservable2().subscribe(p=>{
       // je reçois les nouvelles pages d'Interventions
       this.intervenantsSubject.next(p.content);
       
     });
   
  
   
   this.clientsSouscription=this.clientRepository.getClientspageAsObservable2().subscribe(p=>{
    // je reçois les nouvelles pages d'Interventions
    this.clientsSubject.next(p.content);
    
  });

  this.intervenantnRepository.refreshListe();
  this.clientRepository.refreshListe();
}



   public connectintervenant(id:number){
     this.intervenantnRepository.getintervenant_toconnect(id);
   }
   
   public connectclient(id:number){
    this.clientRepository.getclient_toconnect(id);
    console.log(id);
  }
 

 }