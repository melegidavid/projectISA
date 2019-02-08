import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRentCarProfileComponent } from './admin-rent-car-profile.component';

describe('AdminRentCarProfileComponent', () => {
  let component: AdminRentCarProfileComponent;
  let fixture: ComponentFixture<AdminRentCarProfileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminRentCarProfileComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRentCarProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
