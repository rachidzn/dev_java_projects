import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayPlanningsComponent } from './display-plannings.component';

describe('DisplayPlanningsComponent', () => {
  let component: DisplayPlanningsComponent;
  let fixture: ComponentFixture<DisplayPlanningsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayPlanningsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayPlanningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
