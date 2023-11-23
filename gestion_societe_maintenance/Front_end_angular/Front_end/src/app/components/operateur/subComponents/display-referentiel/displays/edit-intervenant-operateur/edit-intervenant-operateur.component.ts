import { Component, OnInit ,Input,OnChanges, Output, EventEmitter} from '@angular/core';
import { IntervenantRepositoryService } from '../../../../../../services/intervenant-repository.service';

import { Intervenant } from '../../../../../../metier/objet-intervenant';
import { Intervention } from '../../../../../../metier/objet-intervention';
import { InterventionsRepositoryService } from '../../../../../../services/interventions-repository.service';
import { Groupe } from '../../../../../../metier/objet-groupe';
import { GroupeRepositoryService } from '../../../../../../services/groupe-repository.service';

@Component({
  selector: 'app-edit-intervenant-operateur',
  templateUrl: './edit-intervenant-operateur.component.html',
  styleUrls: ['./edit-intervenant-operateur.component.css']
})
export class EditIntervenantOperateurComponent implements OnInit,OnChanges {

  
  @Input() public editIdIntervenant: number;
  
  @Output() public editidintervention: EventEmitter<number>=new EventEmitter<number>();

  public currentIntervenant : Intervenant;
  public interventionsintervenant : Intervention[];
  public groupesintervenant : Groupe[];

  constructor(private IntervenantRepository : IntervenantRepositoryService,private InterventiontRepository : InterventionsRepositoryService,private GroupeRepository : GroupeRepositoryService) {


   }



   public saveIntervenant() {
    
      if (this.currentIntervenant.id > 0){
        let InterToSave = new Intervenant(0,"","","","purple");
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentIntervenant,);

        console.log(InterToSave,);
        this.IntervenantRepository.updateIntervenant(InterToSave,);
        this.currentIntervenant = new Intervenant(0,"","","","purple");
        this.interventionsintervenant=[];
        this.groupesintervenant=[];
      }
      else{
        let InterToSave = new Intervenant(0,"","","","purple");
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentIntervenant,);

        console.log(InterToSave,);
        this.IntervenantRepository.createIntervenant(InterToSave,);
        this.currentIntervenant = new Intervenant(0,"","","","purple");
        this.interventionsintervenant=[];
        this.groupesintervenant=[];
      }
 
    }


  public cancelIntervenant() {
    this.currentIntervenant = new Intervenant(0,"","","","purple");
    this.interventionsintervenant=[];
    this.groupesintervenant=[];
  }

public removeintervenant(){

  this.IntervenantRepository.removeIntervenant(this.currentIntervenant.id,);
  this.currentIntervenant=new Intervenant(0,"","","","purple");
  this.interventionsintervenant=[];
  this.groupesintervenant=[];
}



  ngOnInit() {
    this.currentIntervenant=new Intervenant(0,"","","","purple");
    this.interventionsintervenant=[];
    this.groupesintervenant=[];
  }

  ngOnChanges(changes:any){
    
    if (this.editIdIntervenant==0){
      this.currentIntervenant=new Intervenant(0,"","","","purple");
      this.interventionsintervenant=[];
      this.groupesintervenant=[];
    }
    else{
      this.IntervenantRepository.findById(this.editIdIntervenant).subscribe(Intervenant=> { this.currentIntervenant=Intervenant;
      },);

    this.InterventiontRepository.findintervenantById(this.editIdIntervenant).subscribe(Materiels=> { this.interventionsintervenant=Materiels;
      });console.log(this.interventionsintervenant);

      this.GroupeRepository.findintervenantById(this.editIdIntervenant).subscribe(Materiels=> { this.groupesintervenant=Materiels;
      });

    }
  }


  public editerintervention(id: number) {
    this.editidintervention.emit(id);}



  }