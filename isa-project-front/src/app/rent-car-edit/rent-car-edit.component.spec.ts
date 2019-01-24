import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentCarEditComponent } from './rent-car-edit.component';

describe('RentCarEditComponent', () => {
  let component: RentCarEditComponent;
  let fixture: ComponentFixture<RentCarEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentCarEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentCarEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
