import { Component, OnInit,Input, OnChanges } from '@angular/core';
import { Intervention } from '../../../../metier/objet-intervention';

import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Materiel } from '../../../../metier/objet-materiel';
import { Intervenant } from '../../../../metier/objet-intervenant';

@Component({
  selector: 'app-edit-interventions-operateur',
  templateUrl: './edit-interventions-operateur.component.html',
  styleUrls: ['./edit-interventions-operateur.component.css']
})
export class EditInterventionsOperateurComponent implements OnInit,OnChanges {

  @Input() public editId: number;
  public currentIntervention : Intervention;
  public currentMateriel : Materiel;
  public currentIntervenant : Intervenant;


  constructor(private InterventionRepository : InterventionsRepositoryService) {


   }



   public saveIntervention() {
    
      if (this.currentIntervention.id > 0){
        let InterToSave = new Intervention(0,"1-1-1999","1-1-1999","rien","toi",this.currentMateriel,this.currentIntervenant,"","1-1-1999","1-1-1999","");
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentIntervention);
  
        this.InterventionRepository.updateIntervention(InterToSave);
        this.currentIntervention = new Intervention(0,"1-1-1999","1-1-1999","rien","toi",this.currentMateriel,this.currentIntervenant,"","1-1-1999","1-1-1999","");
      }
 
    }
  public cancelIntervention() {
    this.currentIntervention = new Intervention(0,"1-1-1999","1-1-1999","rien","toi",this.currentMateriel,this.currentIntervenant,"","1-1-1999","1-1-1999","");
  }





  ngOnInit() {
    this.currentIntervention=new Intervention(0,"1-1-1999","1-1-1999","rien","toi",this.currentMateriel,this.currentIntervenant,"","1-1-1999","1-1-1999","");
  }

  ngOnChanges(changes:any){
    
    if (this.editId==0){
      this.currentIntervention=new Intervention(0,"1-1-1999","1-1-1999","rien","toi",this.currentMateriel,this.currentIntervenant,"","1-1-1999","1-1-1999","");
    }
    else{
      this.InterventionRepository.findById(this.editId).subscribe(intervention=> { this.currentIntervention=intervention;
      });
    }
  }



}
