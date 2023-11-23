package com.yaps.petstore.authentication.api;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.service.UserService;
import com.yaps.petstore.domain.constant.AmericanStates;
import com.yaps.petstore.domain.constant.Countries;
import com.yaps.petstore.domain.constant.CreditCardTypes;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.UpdateException;
import com.yaps.petstore.logging.Trace;

@Controller
public class AuthenticationController {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(AuthenticationController.class);
	
	// Used for logging
    private final transient String _cname = this.getClass().getName();
    
    protected String getCname() {
        return _cname;
    }

	@Autowired
	private UserService userService;

	@GetMapping(path = "/login")
	public String login(Authentication authentication) {		// A ce niveau, re-router si déjà authentifié 
		if(authentication != null )
			return "index";
		else
			return "login";
	}

	@GetMapping(path = "/new-account")
	public String newAccount(Model model) {
		model.addAttribute("userDTO", new UserDTO());
		model.addAttribute("states", AmericanStates.getAll());
		model.addAttribute("countries", Countries.getAll());
		model.addAttribute("ccTypes", CreditCardTypes.getAll());
		return "new-account";
	}

	@PostMapping(path = "/new-account")
	public String createAccount(@Valid UserDTO userDTO, Model model) {
		final String mname = "createAccount";
        Trace.entering(getCname(), mname);
		try {
			userDTO.setRoleName("ROLE_USER");
			userService.createUser(userDTO);
			 model.addAttribute("customerCreated",true);
			return "index";
		} catch (CreateException | CheckException e) {
			if(e instanceof DuplicateKeyException)
				model.addAttribute("exception", "Cet identifiant est déjà attribué");
			else
				model.addAttribute("exception", e.getMessage());
			return "error";
		} catch(Exception exc) {
			model.addAttribute("exception", exc.getMessage());
			return "error";
		}
	}
	
	@GetMapping(path = "/update-account/{username}")
	public String showAccount(Model model, @PathVariable String username ) {
		UserDTO userDTO;
		try {
			userDTO = userService.findUser(username);
		} catch (FinderException | CheckException e) {
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		}
		model.addAttribute("userDTO", userDTO);
		return "update-account";
	}

	@PostMapping(path = "/update-account")
	public String updateAccount(@ModelAttribute UserDTO userDTO, Model model) {
		final String mname = "updateAccount";
        Trace.entering(getCname(), mname);
        
		try {
			userService.updateUser(userDTO);
			 model.addAttribute("customerUpdated",true);
			return "index";
		} catch (UpdateException | CheckException e) {
			model.addAttribute("exception", e.getMessage());
			return "error";
		} catch(Exception exc) {
			model.addAttribute("exception", exc.getMessage());
			return "error";
		}

	}
}
