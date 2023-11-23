import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayCatalogOperateurComponent } from './display-catalog-operateur.component';

describe('DisplayCatalogOperateurComponent', () => {
  let component: DisplayCatalogOperateurComponent;
  let fixture: ComponentFixture<DisplayCatalogOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayCatalogOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayCatalogOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
