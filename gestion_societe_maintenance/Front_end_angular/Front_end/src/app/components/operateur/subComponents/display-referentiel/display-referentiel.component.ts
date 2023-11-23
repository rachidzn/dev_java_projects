import { Component, OnInit } from '@angular/core';


@Component({
  selector: 'app-display-referentiel',
  templateUrl: './display-referentiel.component.html',
  styleUrls: ['./display-referentiel.component.css']
})
export class DisplayReferentielComponent implements OnInit {

  public currentIdClient : number;
  public currentIdIntervenant : number;
  public currentIdMateriel : number;
  public currentIdSite : number;

  

  public editClientRequested(id: number) {
    console.log("recus demande edition localisation no " + id);
    this.currentIdClient = id;
  }

  public editIntervenantRequested(id: number) {
 
    this.currentIdIntervenant = id;
  }

  public editMaterielRequested(id: number) {
 
    this.currentIdMateriel = id;
  }
  public editSiteRequested(id: number) {
 
    this.currentIdSite = id;
      

  }


  constructor() {
    //pour le ngfor
    this.currentIdMateriel = 0;

    this.currentIdClient = 0;
    this.currentIdIntervenant = 0;
    this.currentIdSite = 0;
   }

  

  ngOnInit() {
   
  }

}



