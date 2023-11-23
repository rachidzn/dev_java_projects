import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";
import {NgxPageScrollModule} from 'ngx-page-scroll';
import { ChartsModule } from 'ng2-charts';
//import {BsDatepickerModule} from 'ngx-bootstrap/datepicker';
import { PaginationModule } from "ngx-bootstrap/pagination";
import { NgxSmartModalModule } from 'ngx-smart-modal';
import { NgSelectModule } from '@ng-select/ng-select';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import {ScheduleModule} from 'primeng/schedule';
import { FullCalendarModule } from 'ng-fullcalendar';

import { RouterModule } from "@angular/router";

import { AppComponent } from './app.component';
import { ClientComponent } from './components/client/client.component';
import { OperateurComponent } from './components/operateur/operateur.component';
import { IntervenantComponent } from './components/intervenant/intervenant.component';
import { DisplayInterventionsDuclientComponent } from './components/client/subComponents/display-interventions-duclient/display-interventions-duclient.component';
import { SignalementNvProblemeComponent } from './components/client/subComponents/signalement-nv-probleme/signalement-nv-probleme.component';
import { DisplayInterventionsComponent } from './components/intervenant/subComponents/display-interventions/display-interventions.component';
import { DisplayPlanningsComponent } from './components/intervenant/subComponents/display-plannings/display-plannings.component';
import { EditInterventionsComponent } from './components/intervenant/subComponents/edit-interventions/edit-interventions.component';
import { EditPlanningsComponent } from './components/operateur/subComponents/edit-plannings/edit-plannings.component';
import { DisplayReferentielComponent } from './components/operateur/subComponents/display-referentiel/display-referentiel.component';
import { EditReferentielComponent } from './components/operateur/subComponents/edit-referentiel/edit-referentiel.component';



import { VitrineComponent } from './components/vitrine/vitrine.component';

import {DisplayInterventionsOperateurComponent} from './components/operateur/subComponents/display-interventions-operateur/display-interventions-operateur.component';
import { DisplayPlanningsOperateurComponent } from './components/operateur/subComponents/display-plannings-operateur/display-plannings-operateur.component';
import { EditInterventionsOperateurComponent } from './components/operateur/subComponents/edit-interventions-operateur/edit-interventions-operateur.component';
import { InterventionsRepositoryService } from './services/interventions-repository.service';
import { DisplayDashboardComponent } from './components/operateur/subComponents/display-dashboard/display-dashboard.component';
import { DisplayTableauClientComponent } from './components/client/subComponents/display-tableau-client/display-tableau-client.component';
import { DisplayTableauIntervenantComponent } from './components/intervenant/subComponents/display-tableau-intervenant/display-tableau-intervenant.component';
import { DisplayClientComponent } from './components/operateur/subComponents/display-referentiel/displays/display-client/display-client.component';
import { EditClientComponent } from './components/operateur/subComponents/display-referentiel/displays/edit-client/edit-client.component';
import { DisplayIntervenantOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/display-intervenant-operateur/display-intervenant-operateur.component';
import { EditIntervenantOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/edit-intervenant-operateur/edit-intervenant-operateur.component';
import { DisplaySitesOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/display-sites-operateur/display-sites-operateur.component';
import { EditSitesOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/edit-sites-operateur/edit-sites-operateur.component';
import { DisplayMaterielsOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/display-materiels-operateur/display-materiels-operateur.component';
import { EditMaterielsOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/edit-materiels-operateur/edit-materiels-operateur.component';
import { DisplayGrpintervenantOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/display-grpintervenant-operateur/display-grpintervenant-operateur.component';
import { DisplayCatalogOperateurComponent } from './components/operateur/subComponents/display-referentiel/displays/display-catalog-operateur/display-catalog-operateur.component';
import { MessageIntervenantComponent } from './components/intervenant/subComponents/message-intervenant/message-intervenant.component';


import { PDFExportModule } from '@progress/kendo-angular-pdf-export';


@NgModule({
  declarations: [
    AppComponent,
    ClientComponent,
    OperateurComponent,
    IntervenantComponent,
    DisplayInterventionsDuclientComponent,
    SignalementNvProblemeComponent,
    DisplayInterventionsComponent,
    DisplayPlanningsComponent,
    EditInterventionsComponent,
    EditPlanningsComponent,
    DisplayReferentielComponent,
    EditReferentielComponent,
    VitrineComponent,
    DisplayInterventionsOperateurComponent,
    DisplayPlanningsOperateurComponent,
    EditInterventionsOperateurComponent,
    DisplayDashboardComponent,
    DisplayTableauClientComponent,
    DisplayTableauIntervenantComponent,
    DisplayClientComponent,
    EditClientComponent,
    DisplayIntervenantOperateurComponent,
    EditIntervenantOperateurComponent,
    DisplaySitesOperateurComponent,
    EditSitesOperateurComponent,
    DisplayMaterielsOperateurComponent,
    EditMaterielsOperateurComponent,
    DisplayGrpintervenantOperateurComponent,
    DisplayCatalogOperateurComponent,
    MessageIntervenantComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    NgxPageScrollModule,
    ScheduleModule,
    FullCalendarModule,
    //BsDatepickerModule.forRoot(),
    ChartsModule,
    BrowserAnimationsModule, 
    PaginationModule.forRoot(),
    NgxSmartModalModule.forRoot(),
    PDFExportModule,
    NgSelectModule,
    
    
    RouterModule.forRoot([
      {path: 'home', component: VitrineComponent},

      {path: 'operateur', component: OperateurComponent},
      {path: 'operateur/interventions', component: DisplayInterventionsOperateurComponent},
      {path: 'operateur/planning', component: DisplayPlanningsOperateurComponent},
      {path: 'operateur/referentiel', component: DisplayReferentielComponent},

      {path: 'client/message', component: SignalementNvProblemeComponent},
      {path: 'client', component: ClientComponent},
      {path: 'client/interventions', component: DisplayInterventionsDuclientComponent },

      {path: 'intervenant', component: IntervenantComponent},
      {path: 'intervenant/interventions', component: DisplayInterventionsComponent},
      {path: 'intervenant/planning', component: DisplayPlanningsComponent},
      {path: 'intervenant/message', component: MessageIntervenantComponent},
     // {path: 'edit/:id',component: EditKoComponent},
      {path: '', redirectTo: '/home', pathMatch:'full'}
    ])
  ],
  providers: [InterventionsRepositoryService],
  bootstrap: [AppComponent]
})
export class AppModule { }
