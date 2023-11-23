import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageIntervenantComponent } from './message-intervenant.component';

describe('MessageIntervenantComponent', () => {
  let component: MessageIntervenantComponent;
  let fixture: ComponentFixture<MessageIntervenantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageIntervenantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageIntervenantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
