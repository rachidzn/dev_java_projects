import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPlanningsComponent } from './edit-plannings.component';

describe('EditPlanningsComponent', () => {
  let component: EditPlanningsComponent;
  let fixture: ComponentFixture<EditPlanningsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditPlanningsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPlanningsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
