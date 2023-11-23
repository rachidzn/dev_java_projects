import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayMaterielsOperateurComponent } from './display-materiels-operateur.component';

describe('DisplayMaterielsOperateurComponent', () => {
  let component: DisplayMaterielsOperateurComponent;
  let fixture: ComponentFixture<DisplayMaterielsOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayMaterielsOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayMaterielsOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
