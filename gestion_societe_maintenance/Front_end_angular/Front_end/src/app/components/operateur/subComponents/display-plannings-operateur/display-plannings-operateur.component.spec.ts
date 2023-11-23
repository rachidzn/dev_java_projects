import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPlanningsOperateurComponent } from './display-plannings-operateur.component';

describe('DisplayPlanningsOperateurComponent', () => {
  let component: DisplayPlanningsOperateurComponent;
  let fixture: ComponentFixture<DisplayPlanningsOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayPlanningsOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPlanningsOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
