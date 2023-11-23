import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayTableauClientComponent } from './display-tableau-client.component';

describe('DisplayTableauClientComponent', () => {
  let component: DisplayTableauClientComponent;
  let fixture: ComponentFixture<DisplayTableauClientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayTableauClientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayTableauClientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
