import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCarProfileComponent } from './rent-car-profile.component';

describe('RentCarProfileComponent', () => {
  let component: RentCarProfileComponent;
  let fixture: ComponentFixture<RentCarProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCarProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCarProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
