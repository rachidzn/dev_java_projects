package com.yaps.petstore.authentication.domain.service;

import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.model.Role;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

public interface UserService {

    public UserDTO createUser(final UserDTO userDTO) throws CreateException, CheckException;

    public UserDTO findUser(final String userId) throws FinderException, CheckException ;

    public void deleteUser(final String userId) throws RemoveException, CheckException;

    public void updateUser(final UserDTO userDTO) throws UpdateException, CheckException;

    public Iterable<UserDTO> findUsers() throws FinderException;

	public Iterable<UserDTO> findUsersByRole(Role role);

}
