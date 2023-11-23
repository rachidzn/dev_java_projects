import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditInterventionsComponent } from './edit-interventions.component';

describe('EditInterventionsComponent', () => {
  let component: EditInterventionsComponent;
  let fixture: ComponentFixture<EditInterventionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditInterventionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditInterventionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
