import { Component, OnInit, ViewChild } from '@angular/core';
import { Evenement } from '../../../../metier/objet-event';
import { Intervention } from '../../../../metier/objet-intervention';
import { InterventionsRepositoryService } from '../../../../services/interventions-repository.service';
import { Options } from 'fullcalendar';
import { CalendarComponent } from 'ng-fullcalendar';
import { IntervenantRepositoryService } from '../../../../services/intervenant-repository.service';
import { Intervenant } from '../../../../metier/objet-intervenant';

@Component({
  selector: 'app-display-plannings',
  templateUrl: './display-plannings.component.html',
  styleUrls: ['./display-plannings.component.css']
})
export class DisplayPlanningsComponent implements OnInit {
  public Intervenantconnected: Intervenant = new Intervenant(0, "", "", "", "purple");
  public listevents: Evenement[] = [];


  public currentIntervention: Intervention;

  constructor(private interventionRepository: InterventionsRepositoryService, private intervenantRepositoryService: IntervenantRepositoryService) {


  }

  calendarOptions: Options;
  @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;
  ngOnInit() {
    this.intervenantRepositoryService.findById(
      this.intervenantRepositoryService.setintervenant_toconnect()).subscribe(Intervenant => {
      this.Intervenantconnected = Intervenant;
      }, );


    this.interventionRepository.getEventsbyIntervenant(this.intervenantRepositoryService.setintervenant_toconnect()).subscribe(data => {


      this.intervenantRepositoryService.findById(
        this.intervenantRepositoryService.setintervenant_toconnect()).subscribe(Intervenant => {
        this.Intervenantconnected = Intervenant;
        }, );




      this.interventionRepository.getEventsbyIntervenant(this.intervenantRepositoryService.setintervenant_toconnect()).subscribe(data => {

        this.calendarOptions = {

          editable: true,
          eventLimit: false,
          height: 700,
          themeSystem: 'jquery-ui',
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay,listMonth'
          },
          events: data,


        };
      });


      this.interventionRepository.refreshListe();


 });


      }

}
