package com.yaps.petstore.authentication.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.authentication.domain.dao.RoleRepository;
import com.yaps.petstore.authentication.domain.dao.UserRepository;
import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.model.Role;
import com.yaps.petstore.authentication.domain.model.User;
import com.yaps.petstore.domain.model.CreditCard;
import com.yaps.petstore.domain.service.CreditCardService;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;
import com.yaps.petstore.logging.Trace;

/**
 * This class is a facade for all user services.
 */

@Service
public class UserServiceImpl implements UserService{

	// ======================================
    // =             Attributes             =
    // ======================================
    
    @SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
    
 // Used for logging
    protected final transient String _cname = this.getClass().getName();
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private CreditCardService creditCardService;

    // ======================================
    // =            Constructors            =
    // ======================================
    public UserServiceImpl() {}

	// ======================================
    // =           Business methods         =
    // ======================================
    @Override
    @Transactional
    public UserDTO createUser(final UserDTO userDTO) throws CreateException, CheckException {
        final String mname = "createUser";
        Trace.entering(_cname, mname, userDTO);

        if (userDTO == null)
            throw new CreateException("User object is null");
        
        try {
			if(findUser(userDTO.getUsername())!=null)
				 throw new DuplicateKeyException();
		} catch (FinderException | CheckException  e) {}

        // Transforms DTO into domain object
        final User user = new User(userDTO.getUsername(), userDTO.getFirstname(), userDTO.getLastname());        
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setState(userDTO.getState());
        user.setStreet1(userDTO.getStreet1());
        user.setStreet2(userDTO.getStreet2());
        user.setTelephone(userDTO.getTelephone());
        user.setZipcode(userDTO.getZipcode());
        user.setEmail(userDTO.getEmail());
        
        // ======================================
    	// = Credit Card Check =
    	// ======================================
        if(	userDTO.getCreditCardNumber()!=null && userDTO.getCreditCardType()!=null
           	 && userDTO.getCreditCardExpiryDate()!=null ) {
       	
       	if (!userDTO.getCreditCardNumber().equals("") && !userDTO.getCreditCardType().equals("") 
       			&& !userDTO.getCreditCardExpiryDate().equals("")){

        CreditCard cc = new CreditCard();
        cc.setCreditCardNumber(userDTO.getCreditCardNumber());
        cc.setCreditCardType(userDTO.getCreditCardType());
        cc.setCreditCardExpiryDate(userDTO.getCreditCardExpiryDate());
        creditCardService.verifyCreditCard(cc);
        
        user.setCreditCardExpiryDate(userDTO.getCreditCardExpiryDate());
        user.setCreditCardNumber(userDTO.getCreditCardNumber());
        user.setCreditCardType(userDTO.getCreditCardType());
        }        
        }
        /* *** ADDED *** */
        user.setRole(roleRepository.findByName(userDTO.getRoleName()));
        /* *** ADDED *** */
//        logger.info("userDTO.password is ... "+userDTO.getPassword());
        if(userDTO.getPassword().length()<4)
        	throw new CreateException("password's length exception (mini. of 4 char. required)");
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        
        user.checkData();
     
        // Creates the object
        userRepository.save(user);
        // Transforms domain object into DTO
        final UserDTO result = transformUser2DTO(user);

        Trace.exiting(_cname, mname, result);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public UserDTO findUser(final String username) throws FinderException, CheckException {
        final String mname = "findUser";
        Trace.entering(_cname, mname, username);

    	checkId(username);
        // Finds the object
        User user=null;
        if( ! userRepository.findById(username).isPresent())
        	throw new FinderException("User must exist to be found");
        else 
        	user=userRepository.findById(username).get();

        // Transforms domain object into DTO
        final UserDTO userDTO = transformUser2DTO(user);

        Trace.exiting(_cname, mname, userDTO);
        return userDTO;
    }

    @Override
    @Transactional
    public void deleteUser(final String username) throws RemoveException, CheckException {
        final String mname = "deleteUser";
        Trace.entering(_cname, mname, username);
        User user;
    	checkId(username);
    	if( ! userRepository.findById(username).isPresent())
    		throw new RemoveException("User must exist to be deleted");
        else 
        	user=userRepository.findById(username).get();

        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUser(final UserDTO userDTO) throws UpdateException, CheckException {
        final String mname = "updateUser";
        Trace.entering(_cname, mname, userDTO);

        if (userDTO == null)
            throw new UpdateException("User object is null");

    	checkId(userDTO.getUsername());

    	final User user;

        // Checks if the object exists
    	if( ! userRepository.findById(userDTO.getUsername()).isPresent())
    		throw new UpdateException("User must exist to be updated"); 
        else 
        	user=userRepository.findById(userDTO.getUsername()).get();

        // Transforms DTO into domain object
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setState(userDTO.getState());
        user.setStreet1(userDTO.getStreet1());
        user.setStreet2(userDTO.getStreet2());
        user.setTelephone(userDTO.getTelephone());
        user.setZipcode(userDTO.getZipcode());
        user.setEmail(userDTO.getEmail());
     // ======================================
    	// = Credit Card Check =
    	// ======================================
        if(		userDTO.getCreditCardNumber()!="" && userDTO.getCreditCardNumber()!=null
        		&& userDTO.getCreditCardType()!="" && userDTO.getCreditCardType()!=null
        		&& userDTO.getCreditCardExpiryDate()!="" && userDTO.getCreditCardExpiryDate()!=null ) {

        CreditCard cc = new CreditCard();
        cc.setCreditCardNumber(userDTO.getCreditCardNumber());
        cc.setCreditCardType(userDTO.getCreditCardType());
        cc.setCreditCardExpiryDate(userDTO.getCreditCardExpiryDate());
        creditCardService.verifyCreditCard(cc);
        
        user.setCreditCardExpiryDate(userDTO.getCreditCardExpiryDate());
        user.setCreditCardNumber(userDTO.getCreditCardNumber());
        user.setCreditCardType(userDTO.getCreditCardType());
        }    
        /* *** ADDED *** */
        if(userDTO.getPassword().length()<4)
        	throw new UpdateException("password's length exception (mini. of 4 char. required)");
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));

        user.checkData();
     // Updates the object
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<UserDTO> findUsers() {
        final String mname = "findUsers";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Iterable<User> users = userRepository.findAll();

        // Transforms domain objects into DTOs
        final Iterable<UserDTO> usersDTO = transformUsers2DTOs(users);

        Trace.exiting(_cname, mname, new Integer(((Collection<UserDTO>) usersDTO).size()));
        return usersDTO;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Iterable<UserDTO> findUsersByRole(Role role) {
        final String mname = "findUsersByRole";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Iterable<User> users = userRepository.findAllByRole(role);

        // Transforms domain objects into DTOs
        final Iterable<UserDTO> usersDTO = transformUsers2DTOs(users);

        Trace.exiting(_cname, mname, new Integer(((Collection<UserDTO>) usersDTO).size()));
        return usersDTO;
    }

    // ======================================
    // =          Private Methods           =
    // ======================================
    private UserDTO transformUser2DTO(final User user) {
        final UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setTelephone(user.getTelephone());
        userDTO.setEmail(user.getEmail());      
        if(user.getAddress()!=null) {
        	userDTO.setStreet1(user.getStreet1());
            userDTO.setStreet2(user.getStreet2());
            userDTO.setCity(user.getCity());
            userDTO.setZipcode(user.getZipcode());
            userDTO.setState(user.getState());
            userDTO.setCountry(user.getCountry());
        }
        if(user.getCreditCard()!=null) {
        userDTO.setCreditCardNumber(user.getCreditCardNumber());
        userDTO.setCreditCardType(user.getCreditCardType());
        userDTO.setCreditCardExpiryDate(user.getCreditCardExpiryDate());
        }
        userDTO.setPassword(user.getPassword());
        userDTO.setRoleName(user.getRole().getName());
        return userDTO;
    }

    private Iterable<UserDTO> transformUsers2DTOs(final Iterable<User> users) {
        final Collection<UserDTO> usersDTO = new ArrayList<>();
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
            final User user = iterator.next();
            usersDTO.add(transformUser2DTO(user));
        }
        return usersDTO;
    }
    
    private void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }

}
