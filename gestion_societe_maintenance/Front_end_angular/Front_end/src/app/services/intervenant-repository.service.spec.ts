import { TestBed, inject } from '@angular/core/testing';

import { IntervenantRepositoryService } from './intervenant-repository.service';

describe('IntervenantRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IntervenantRepositoryService]
    });
  });

  it('should be created', inject([IntervenantRepositoryService], (service: IntervenantRepositoryService) => {
    expect(service).toBeTruthy();
    
  }));
});
