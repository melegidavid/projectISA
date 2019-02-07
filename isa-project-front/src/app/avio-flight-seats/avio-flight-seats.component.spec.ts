import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AvioFlightSeatsComponent } from './avio-flight-seats.component';

describe('AvioFlightSeatsComponent', () => {
  let component: AvioFlightSeatsComponent;
  let fixture: ComponentFixture<AvioFlightSeatsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AvioFlightSeatsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AvioFlightSeatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
