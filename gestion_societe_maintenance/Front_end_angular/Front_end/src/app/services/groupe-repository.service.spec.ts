import { TestBed, inject } from '@angular/core/testing';

import { GroupeRepositoryService } from './groupe-repository.service';

describe('GroupeRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GroupeRepositoryService]
    });
  });

  it('should be created', inject([GroupeRepositoryService], (service: GroupeRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
