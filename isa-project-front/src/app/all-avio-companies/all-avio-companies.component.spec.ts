import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AllAvioCompaniesComponent } from './all-avio-companies.component';

describe('AllAvioCompaniesComponent', () => {
  let component: AllAvioCompaniesComponent;
  let fixture: ComponentFixture<AllAvioCompaniesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllAvioCompaniesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllAvioCompaniesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
