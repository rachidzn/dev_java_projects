package com.GroupeC.LoncotoSpring.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.GroupeC.LoncotoSpring.metier.Catalogue;

public interface CatalogueRepository extends PagingAndSortingRepository<Catalogue, Integer> {

}
