import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInterventionsOperateurComponent } from './edit-interventions-operateur.component';

describe('EditInterventionsOperateurComponent', () => {
  let component: EditInterventionsOperateurComponent;
  let fixture: ComponentFixture<EditInterventionsOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditInterventionsOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInterventionsOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
