import { TestBed, inject } from '@angular/core/testing';

import { ClientRepositoryService } from './client-repository.service';

describe('ClientRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ClientRepositoryService]
    });
  });

  it('should be created', inject([ClientRepositoryService], (service: ClientRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
