import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayInterventionsDuclientComponent } from './display-interventions-duclient.component';

describe('DisplayInterventionsDuclientComponent', () => {
  let component: DisplayInterventionsDuclientComponent;
  let fixture: ComponentFixture<DisplayInterventionsDuclientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayInterventionsDuclientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayInterventionsDuclientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
