import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMaterielsOperateurComponent } from './edit-materiels-operateur.component';

describe('EditMaterielsOperateurComponent', () => {
  let component: EditMaterielsOperateurComponent;
  let fixture: ComponentFixture<EditMaterielsOperateurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditMaterielsOperateurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMaterielsOperateurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
