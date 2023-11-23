import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSitesOperateurComponent } from './edit-sites-operateur.component';

describe('EditSitesOperateurComponent', () => {
  let component: EditSitesOperateurComponent;
  let fixture: ComponentFixture<EditSitesOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditSitesOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSitesOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
