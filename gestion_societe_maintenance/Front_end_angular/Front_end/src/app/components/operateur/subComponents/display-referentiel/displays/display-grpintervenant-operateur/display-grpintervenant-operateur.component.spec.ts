import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayGrpintervenantOperateurComponent } from './display-grpintervenant-operateur.component';

describe('DisplayGrpintervenantOperateurComponent', () => {
  let component: DisplayGrpintervenantOperateurComponent;
  let fixture: ComponentFixture<DisplayGrpintervenantOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayGrpintervenantOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayGrpintervenantOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
