import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllRentACarsComponent } from './all-rent-a-cars.component';

describe('AllRentACarsComponent', () => {
  let component: AllRentACarsComponent;
  let fixture: ComponentFixture<AllRentACarsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllRentACarsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllRentACarsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
