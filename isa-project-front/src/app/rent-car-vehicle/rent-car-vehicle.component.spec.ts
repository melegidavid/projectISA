import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCarVehicleComponent } from './rent-car-vehicle.component';

describe('RentCarVehicleComponent', () => {
  let component: RentCarVehicleComponent;
  let fixture: ComponentFixture<RentCarVehicleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCarVehicleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCarVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
