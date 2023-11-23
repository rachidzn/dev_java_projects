package com.yaps.petstore.authentication.domain.service;

import com.yaps.petstore.authentication.domain.model.Role;
import com.yaps.petstore.exception.FinderException;

public interface RoleService {
	
	public Role findByRoleName(String roleName) throws FinderException;

}
