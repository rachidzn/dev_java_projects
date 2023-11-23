import { Component, OnInit,EventEmitter, Output } from '@angular/core';
import { SiteRepositoryService } from '../../../../../../services/site-repository.service';
import { Subject, Subscription } from 'rxjs';
import { Site } from '../../../../../../metier/objet-site';


@Component({
  selector: 'app-display-sites-operateur',
  templateUrl: './display-sites-operateur.component.html',
  styleUrls: ['./display-sites-operateur.component.css']
})
export class DisplaySitesOperateurComponent implements OnInit {


  public sitesSubject : Subject<Site[]>
  private clientsSouscription : Subscription;
  public totalItems:number;
  public currentPage : number;
  public taillePage : number;
 
  @Output() public showdetails: EventEmitter<number>=new EventEmitter<number>();;


  constructor(private siteRepository: SiteRepositoryService) {
    //pour le ngfor
    this.sitesSubject= new Subject<Site[]>();
   }

  

public pageChanged(event){
  this.siteRepository.setnopage(event.page-1);
}
  ngOnInit() {
    this.clientsSouscription=this.siteRepository.getSitesAsObservable().subscribe(p=>{
      // je reçois les nouvelles pages d'clients
      this.sitesSubject.next(p.content);
      this.totalItems=p.totalElements;
      this.currentPage=p.number+1;
      this.taillePage=p.size;
    });
    this.siteRepository.refreshListe();
  }

  public details(id: number) {
    this.showdetails.emit(id);

  }
}
