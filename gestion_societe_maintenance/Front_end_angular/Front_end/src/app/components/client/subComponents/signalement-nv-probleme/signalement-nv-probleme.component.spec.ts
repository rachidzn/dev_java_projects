import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignalementNvProblemeComponent } from './signalement-nv-probleme.component';

describe('SignalementNvProblemeComponent', () => {
  let component: SignalementNvProblemeComponent;
  let fixture: ComponentFixture<SignalementNvProblemeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignalementNvProblemeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignalementNvProblemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
