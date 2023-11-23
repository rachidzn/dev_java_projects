import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayInterventionsOperateurComponent } from './display-interventions-operateur.component';

describe('DisplayInterventionsOperateurComponent', () => {
  let component: DisplayInterventionsOperateurComponent;
  let fixture: ComponentFixture<DisplayInterventionsOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayInterventionsOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayInterventionsOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
