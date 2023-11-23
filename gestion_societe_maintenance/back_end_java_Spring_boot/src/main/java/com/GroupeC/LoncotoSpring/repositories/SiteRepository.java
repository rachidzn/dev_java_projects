package com.GroupeC.LoncotoSpring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.GroupeC.LoncotoSpring.metier.Site;

@RestResource
public interface SiteRepository extends PagingAndSortingRepository<Site, Integer> {

}
