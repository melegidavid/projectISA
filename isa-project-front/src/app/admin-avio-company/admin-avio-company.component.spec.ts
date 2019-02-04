import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAvioCompanyComponent } from './admin-avio-company.component';

describe('AdminAvioCompanyComponent', () => {
  let component: AdminAvioCompanyComponent;
  let fixture: ComponentFixture<AdminAvioCompanyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAvioCompanyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAvioCompanyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
