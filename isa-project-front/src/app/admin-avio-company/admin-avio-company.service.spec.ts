import { TestBed } from '@angular/core/testing';

import { AdminAvioCompanyService } from './admin-avio-company.service';

describe('AdminAvioComanyService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AdminAvioCompanyService = TestBed.get(AdminAvioCompanyService);
    expect(service).toBeTruthy();
  });
});
