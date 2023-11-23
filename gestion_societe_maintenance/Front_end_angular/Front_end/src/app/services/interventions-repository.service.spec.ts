import { TestBed, inject } from '@angular/core/testing';

import { InterventionsRepositoryService } from './interventions-repository.service';

describe('InterventionsRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InterventionsRepositoryService]
    });
  });

  it('should be created', inject([InterventionsRepositoryService], (service: InterventionsRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
