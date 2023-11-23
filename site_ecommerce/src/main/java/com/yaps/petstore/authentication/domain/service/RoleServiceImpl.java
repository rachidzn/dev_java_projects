package com.yaps.petstore.authentication.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yaps.petstore.authentication.domain.dao.RoleRepository;
import com.yaps.petstore.authentication.domain.model.Role;
import com.yaps.petstore.exception.FinderException;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByRoleName(String roleName) throws FinderException {
		Role role;
		if( (role=roleRepository.findByName(roleName))==null)
			throw new FinderException("role unknown");
		return role;
	}

}
