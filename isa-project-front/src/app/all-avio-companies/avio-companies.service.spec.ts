import { TestBed } from '@angular/core/testing';

import { AvioCompaniesService } from './avio-companies.service';

describe('AvioCompaniesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AvioCompaniesService = TestBed.get(AvioCompaniesService);
    expect(service).toBeTruthy();
  });
});
