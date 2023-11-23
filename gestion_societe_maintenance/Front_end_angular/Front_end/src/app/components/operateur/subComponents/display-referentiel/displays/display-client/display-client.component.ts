import { Component, OnInit,EventEmitter, Output } from '@angular/core';
import { ClientRepositoryService } from '../../../../../../services/client-repository.service';
import { Subject, Subscription } from 'rxjs';
import { Client } from '../../../../../../metier/objet-client';

@Component({
  selector: 'app-display-client',
  templateUrl: './display-client.component.html',
  styleUrls: ['./display-client.component.css']
})
export class DisplayClientComponent implements OnInit {

 
  public clientsSubject : Subject<Client[]>
  private clientsSouscription : Subscription;
  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
 
  @Output() public showdetails: EventEmitter<number>=new EventEmitter<number>();;


  constructor(private clientRepository: ClientRepositoryService) {
    //pour le ngfor
    this.clientsSubject= new Subject<Client[]>();
   }

  

public pageChanged(event){
  this.clientRepository.setnopage(event.page-1);
}
  ngOnInit() {
    this.clientsSouscription=this.clientRepository.getClientAsObservable().subscribe(p=>{
      // je reçois les nouvelles pages d'clients
      this.clientsSubject.next(p.content);
      this.totalItems=p.totalElements;
      this.currentPage=p.number+1;
      this.taillePage=p.size;
    });
    this.clientRepository.refreshListe();
  }

  public details(id: number) {
    this.showdetails.emit(id);

  }

  
}




