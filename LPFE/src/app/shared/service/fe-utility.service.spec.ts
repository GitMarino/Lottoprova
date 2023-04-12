import { TestBed } from '@angular/core/testing';

import { FEutilityService } from './fe-utility.service';

describe('InternalService', () => {
  let service: FEutilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FEutilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
