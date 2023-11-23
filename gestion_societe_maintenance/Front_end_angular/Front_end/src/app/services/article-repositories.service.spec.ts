import { TestBed, inject } from '@angular/core/testing';

import { ArticleRepositoriesService } from './article-repositories.service';

describe('ArticleRepositoriesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ArticleRepositoriesService]
    });
  });

  it('should be created', inject([ArticleRepositoriesService], (service: ArticleRepositoriesService) => {
    expect(service).toBeTruthy();
  }));
});
