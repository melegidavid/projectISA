import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCarSearchComponent } from './rent-car-search.component';

describe('RentCarSearchComponent', () => {
  let component: RentCarSearchComponent;
  let fixture: ComponentFixture<RentCarSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCarSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCarSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
