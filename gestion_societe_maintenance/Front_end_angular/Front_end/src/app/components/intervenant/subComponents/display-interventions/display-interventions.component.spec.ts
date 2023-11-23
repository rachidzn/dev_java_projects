import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayInterventionsComponent } from './display-interventions.component';

describe('DisplayInterventionsComponent', () => {
  let component: DisplayInterventionsComponent;
  let fixture: ComponentFixture<DisplayInterventionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayInterventionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayInterventionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
