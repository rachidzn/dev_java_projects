import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayReferentielComponent } from './display-referentiel.component';

describe('DisplayReferentielComponent', () => {
  let component: DisplayReferentielComponent;
  let fixture: ComponentFixture<DisplayReferentielComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayReferentielComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayReferentielComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
