import { Component, OnInit, OnChanges, Input } from '@angular/core';
import { Intervention } from '../../../../metier/objet-intervention';
import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Materiel } from '../../../../metier/objet-materiel';
import { Intervenant } from '../../../../metier/objet-intervenant';


@Component({
  selector: 'app-edit-interventions',
  templateUrl: './edit-interventions.component.html',
  styleUrls: ['./edit-interventions.component.css']
})
export class EditInterventionsComponent implements OnInit {

public currentMateriel :Materiel;
public currentIntervenant:Intervenant;
 
@Input() public editId: number;
  public currentIntervention : Intervention;
  MaterielRepository: any;

  constructor(private InterventionRepository : InterventionsRepositoryService) {


   }



   public saveIntervention() {
    
      if (this.currentIntervention.id > 0){
        let InterToSave = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
        InterToSave.copyFrom(this.currentIntervention);
  
        this.InterventionRepository.updateIntervention(InterToSave);
        this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
      }
 
    }
  public cancelIntervention() {
    this.currentIntervention = new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
  }

  ngOnInit() {
    this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");

  }

  ngOnChanges(changes:any){
    
    if (this.editId==0){
      this.currentIntervention=new Intervention(0,"","","En Attente","",this.currentMateriel,this.currentIntervenant,"","","","");
    }
    else{
      this.InterventionRepository.findById(this.editId).subscribe(intervention=> { this.currentIntervention=intervention;
      });
    }
  }


}