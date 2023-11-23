import { TestBed, inject } from '@angular/core/testing';

import { MaterielRepositoryService } from './materiel-repository.service';

describe('MaterielRepositoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MaterielRepositoryService]
    });
  });

  it('should be created', inject([MaterielRepositoryService], (service: MaterielRepositoryService) => {
    expect(service).toBeTruthy();
  }));
});
