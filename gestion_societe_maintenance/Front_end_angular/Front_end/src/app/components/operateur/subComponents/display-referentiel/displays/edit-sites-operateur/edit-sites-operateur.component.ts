import { Component, OnInit ,Input,OnChanges} from '@angular/core';
import { Site } from '../../../../../../metier/objet-site';

import { SiteRepositoryService } from '../../../../../../services/site-repository.service';
import { Materiel } from '../../../../../../metier/objet-materiel';
import { MaterielRepositoryService } from '../../../../../../services/materiel-repository.service';


@Component({
  selector: 'app-edit-sites-operateur',
  templateUrl: './edit-sites-operateur.component.html',
  styleUrls: ['./edit-sites-operateur.component.css']
})
export class EditSitesOperateurComponent implements OnInit {

  @Input() public editIdSite: number;
  public currentSite : Site;
  public materielssite : Materiel[];

  constructor(private SiteRepository : SiteRepositoryService,private MaterielRepository : MaterielRepositoryService) {


   }



   public saveSite() {
    
      if (this.currentSite.id > 0){
        let InterToSave = new Site(0,"","",0);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentSite);

        console.log(InterToSave);
        this.SiteRepository.updateSite(InterToSave);
        this.currentSite = new Site(0,"","",0);
        this.materielssite=[];
      }
      else{
        let InterToSave = new Site(0,"","",0);
        
        // retransformation du modele/json du formulaire
        // en veritable objet Livre avec ses méthodes
        InterToSave.copyFrom(this.currentSite);

        console.log(InterToSave);
        this.SiteRepository.createSite(InterToSave);
        this.currentSite = new Site(0,"","",0);
        this.materielssite=[];
      }
 
    }


  public cancelSite() {
    this.currentSite = new Site(0,"","",0);
    this.materielssite=[];
  }

public removeSite(){

  this.SiteRepository.removeSite(this.currentSite.id);
  this.currentSite=new Site(0,"","",0);
  this.materielssite=[];
}



  ngOnInit() {
    this.currentSite=new Site(0,"","",0);
    this.materielssite=[];
    this.SiteRepository.refreshListe();
    this.MaterielRepository.refreshListe();
  }

  ngOnChanges(changes:any){
    
    if (this.editIdSite==0){
      this.currentSite=new Site(0,"","",0);
      this.materielssite=[];
    }
    else{
      
      this.SiteRepository.findById(this.editIdSite).subscribe(Site=> { this.currentSite=Site;
      });
    
      this.MaterielRepository.findsiteById(this.editIdSite).subscribe(Materiels=> { this.materielssite=Materiels;
      });
    
    }
  }



}
