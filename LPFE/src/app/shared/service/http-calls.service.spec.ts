import { TestBed } from '@angular/core/testing';

import { HttpCallsService } from './http-calls.service';

describe('HttpCallsServiceService', () => {
  let service: HttpCallsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpCallsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
