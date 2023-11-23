package com.GroupeC.LoncotoSpring.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.GroupeC.LoncotoSpring.metier.Client;

@RestResource
public interface ClientRepository extends PagingAndSortingRepository<Client, Integer> {
	
}
