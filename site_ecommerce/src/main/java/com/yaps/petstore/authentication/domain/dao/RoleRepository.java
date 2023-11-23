package com.yaps.petstore.authentication.domain.dao;

import org.springframework.data.repository.CrudRepository;

import com.yaps.petstore.authentication.domain.model.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
	public Role findByName(String name);
}
