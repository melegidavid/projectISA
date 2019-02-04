import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HotelMenuItemReservationComponent } from './hotel-menu-item-reservation.component';

describe('HotelMenuItemReservationComponent', () => {
  let component: HotelMenuItemReservationComponent;
  let fixture: ComponentFixture<HotelMenuItemReservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HotelMenuItemReservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HotelMenuItemReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
