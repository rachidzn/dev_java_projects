import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayIntervenantOperateurComponent } from './display-intervenant-operateur.component';

describe('DisplayIntervenantOperateurComponent', () => {
  let component: DisplayIntervenantOperateurComponent;
  let fixture: ComponentFixture<DisplayIntervenantOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayIntervenantOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayIntervenantOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
