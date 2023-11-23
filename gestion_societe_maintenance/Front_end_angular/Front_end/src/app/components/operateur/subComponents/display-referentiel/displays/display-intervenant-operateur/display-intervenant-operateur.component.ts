import { Component, OnInit,EventEmitter, Output } from '@angular/core';
import { IntervenantRepositoryService } from '../../../../../../services/intervenant-repository.service';
import { Subject, Subscription } from 'rxjs';
import { Intervenant } from '../../../../../../metier/objet-intervenant';

@Component({
  selector: 'app-display-intervenant-operateur',
  templateUrl: './display-intervenant-operateur.component.html',
  styleUrls: ['./display-intervenant-operateur.component.css']
})
export class DisplayIntervenantOperateurComponent implements OnInit {

  public intervenantsSubject : Subject<Intervenant[]>
  private clientsSouscription : Subscription;
  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
 
  @Output() public showdetails: EventEmitter<number>=new EventEmitter<number>();;


  constructor(private intervenantRepository: IntervenantRepositoryService) {
    //pour le ngfor
    this.intervenantsSubject= new Subject<Intervenant[]>();
   }

  

public pageChanged(event){
  this.intervenantRepository.setnopage(event.page-1);
}
  ngOnInit() {
    this.clientsSouscription=this.intervenantRepository.getIntervenantsAsObservable().subscribe(p=>{
      // je reçois les nouvelles pages d'clients
      this.intervenantsSubject.next(p.content);
      this.totalItems=p.totalElements;
      this.currentPage=p.number+1;
      this.taillePage=p.size;
    });
    this.intervenantRepository.refreshListe();
  }

  public details(id: number) {
    this.showdetails.emit(id);

  }
}
