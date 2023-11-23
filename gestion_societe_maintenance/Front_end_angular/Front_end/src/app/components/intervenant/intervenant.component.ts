import { Component, OnInit } from '@angular/core';
import { Intervenant } from '../../metier/objet-intervenant';
import { IntervenantRepositoryService } from '../../services/intervenant-repository.service';

@Component({
  selector: 'app-intervenant',
  templateUrl: './intervenant.component.html',
  styleUrls: ['./intervenant.component.css']
})
export class IntervenantComponent implements OnInit {
public Intervenantconnected: Intervenant=new Intervenant(0,"","","","purple");
  public currentEditId : number;

 constructor( private intervenantRepositoryService: IntervenantRepositoryService) {
    this.currentEditId = 0;
  }

  ngOnInit() {
/*à l'inititation du composant ( donc à la "connexion")  , 
je vais chercher avec un findbyid l'intervenant séléctionner dans l'authentification    */


   this.intervenantRepositoryService.findById(
     this.intervenantRepositoryService.setintervenant_toconnect()).subscribe(Intervenant=> { this.Intervenantconnected=Intervenant;
   },);


  }
 
  
}
