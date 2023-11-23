import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplaySitesOperateurComponent } from './display-sites-operateur.component';

describe('DisplaySitesOperateurComponent', () => {
  let component: DisplaySitesOperateurComponent;
  let fixture: ComponentFixture<DisplaySitesOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplaySitesOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplaySitesOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
