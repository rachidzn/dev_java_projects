import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-operateur',
  templateUrl: './operateur.component.html',
  styleUrls: ['./operateur.component.css']
})
export class OperateurComponent implements OnInit {

  public currentIdintervention : number;
  
  
  
  public editInterventionRequested(id: number) {
 
    this.currentIdintervention = id;
      

  }
  
  
  constructor() {   this.currentIdintervention = 0;}




  ngOnInit() {
  }


}
