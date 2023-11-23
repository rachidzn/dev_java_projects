import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayTableauIntervenantComponent } from './display-tableau-intervenant.component';

describe('DisplayTableauIntervenantComponent', () => {
  let component: DisplayTableauIntervenantComponent;
  let fixture: ComponentFixture<DisplayTableauIntervenantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayTableauIntervenantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayTableauIntervenantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
