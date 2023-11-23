import { TestBed, inject } from '@angular/core/testing';

import { SiteRepositoryService } from './site-repository.service';

describe('SiteRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SiteRepositoryService]
    });
  });

  it('should be created', inject([SiteRepositoryService], (service: SiteRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
