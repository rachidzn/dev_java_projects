import { Component, OnInit ,Input,OnChanges, EventEmitter, Output} from '@angular/core';
import { Client } from '../../../../../../metier/objet-client';

import { ClientRepositoryService } from '../../../../../../services/client-repository.service';
import { MaterielRepositoryService } from '../../../../../../services/materiel-repository.service';
import { Materiel } from '../../../../../../metier/objet-materiel';

@Component({
  selector: 'app-edit-client',
  templateUrl: './edit-client.component.html',
  styleUrls: ['./edit-client.component.css']
})
export class EditClientComponent implements OnInit, OnChanges {

  @Input() public editIdClient: number;
  @Output() public editid2: EventEmitter<number>=new EventEmitter<number>();
  public currentClient : Client;
  public materielclient : Materiel[];
  constructor(private ClientRepository : ClientRepositoryService,private MaterielRepository : MaterielRepositoryService) {
    

   }



   public saveClient() {
    
      if (this.currentClient.id > 0){
        let InterToSave = new Client(0,"","",0);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentClient);

        console.log(InterToSave);
        this.ClientRepository.updateClient(InterToSave);
        this.currentClient = new Client(0,"","",0);
        this.materielclient=[];
      }
      else{
        let InterToSave = new Client(0,"","",0);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentClient);

        console.log(InterToSave);
        this.ClientRepository.createClient(InterToSave);
        this.currentClient = new Client(0,"","",0);
        this.materielclient=[];
      }
 
    }


  public cancelClient() {
    this.currentClient = new Client(0,"","",0);
    this.materielclient=[];
  }

public removeclient(){

  this.ClientRepository.removeClient(this.currentClient.id);
  this.currentClient=new Client(0,"","",0);
  this.materielclient=[];
}



  ngOnInit() {
    this.currentClient=new Client(0,"","",0);
    this.materielclient=[];
  }

  ngOnChanges(changes:any){
    
    if (this.editIdClient==0){
      this.currentClient=new Client(0,"","",0);
      this.materielclient=[];
    }
    else{
      this.ClientRepository.findById(this.editIdClient).subscribe(Client=> { this.currentClient=Client;
      });
      
      this.MaterielRepository.findclientById(this.editIdClient).subscribe(Materiels=> { this.materielclient=Materiels;
      });console.log(this.materielclient);

    }
  }


  public editermateriel(id: number) {
    this.editid2.emit(id);

  }

}
