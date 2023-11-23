import { Component, OnInit } from '@angular/core';
import { GroupeRepositoryService } from '../../../../../../services/groupe-repository.service';
import { Subject, Subscription } from 'rxjs';
import { Groupe } from '../../../../../../metier/objet-groupe';



@Component({
  selector: 'app-display-grpintervenant-operateur',
  templateUrl: './display-grpintervenant-operateur.component.html',
  styleUrls: ['./display-grpintervenant-operateur.component.css']
})
export class DisplayGrpintervenantOperateurComponent implements OnInit {

 
  public GroupesSubject : Subject<Groupe[]>
  private GroupesSouscription : Subscription;
  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
  public currentGroupe : Groupe;
  public editIdGroupe: number;

  constructor(private GroupeRepository: GroupeRepositoryService) {
    //pour le ngfor
    this.GroupesSubject= new Subject<Groupe[]>();
   }

  

public pageChanged(event){
  this.GroupeRepository.setnopage(event.page-1);
}
  ngOnInit() {
    this.GroupesSouscription=this.GroupeRepository.getGroupeAsObservable().subscribe(p=>{
      // je reçois les nouvelles pages d'Groupes
      this.GroupesSubject.next(p.content);
      this.totalItems=p.totalElements;
      this.currentPage=p.number+1;
      this.taillePage=p.size;
    });
    this.currentGroupe=new Groupe(0,"");
    this.GroupeRepository.refreshListe();
  }

  public details(id: number) {
    this.editIdGroupe=id;
    this.GroupeRepository.findById(this.editIdGroupe).subscribe(Groupe=> { this.currentGroupe=Groupe;
    });
  }

  

  public saveGroupe() {
    
    if (this.currentGroupe.id > 0){
      let InterToSave = new Groupe(0,"");
      
      // retransformation du modele/json du formulaire
      // en veritable objet Livre avec ses méthodes
      InterToSave.copyFrom(this.currentGroupe);

      console.log(InterToSave);
      this.GroupeRepository.updateGroupe(InterToSave);
      this.currentGroupe = new Groupe(0,"");
    }
    else{
      let InterToSave = new Groupe(0,"");
      
      // retransformation du modele/json du formulaire
      // en veritable objet Livre avec ses méthodes
      InterToSave.copyFrom(this.currentGroupe);

      console.log(InterToSave);
      this.GroupeRepository.createGroupe(InterToSave);
      this.currentGroupe = new Groupe(0,"");
    }

  }


public cancelGroupe() {
  this.currentGroupe = new Groupe(0,"");
}

public removeGroupe(){

this.GroupeRepository.removeGroupe(this.currentGroupe.id);
this.currentGroupe=new Groupe(0,"");
}



/*
ngOnChanges(changes:any){
  
  if (this.editIdGroupe==0){
    this.currentGroupe=new Groupe(0,"");
  }
  else{
    this.GroupeRepository.findById(this.editIdGroupe).subscribe(Groupe=> { this.currentGroupe=Groupe;
    });
  }
}*/
}











