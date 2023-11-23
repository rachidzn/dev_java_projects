import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditIntervenantOperateurComponent } from './edit-intervenant-operateur.component';

describe('EditIntervenantOperateurComponent', () => {
  let component: EditIntervenantOperateurComponent;
  let fixture: ComponentFixture<EditIntervenantOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditIntervenantOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditIntervenantOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
