package com.yaps.petstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.yaps.petstore.authentication.domain.dto.UserDTO;
import com.yaps.petstore.authentication.domain.service.RoleService;
import com.yaps.petstore.authentication.domain.service.UserService;
import com.yaps.petstore.exception.FinderException;

/**
 * This servlet displays user according to their role.
 */
@Controller
public class FindUsersController  {
	
	// Used for logging
    private final transient String _cname = this.getClass().getName();
    
    protected String getCname() {
        return _cname;
    }
    
    @Autowired
	private UserService userService;
    
    @Autowired
	private RoleService roleService;
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/displayFranchisees")
    public String showFranchisees(Model model) {
    	Iterable<UserDTO> franchiseesDTO = null;
    	try {
    		franchiseesDTO = userService.findUsersByRole(roleService.findByRoleName("ROLE_FRANCHISEE"));
		} catch (FinderException e) {
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
    	model.addAttribute("franchiseesDTO", franchiseesDTO);
    	return "display-users";
    }
    
    @Secured({"ROLE_ADMIN","ROLE_FRANCHISEE"})
    @GetMapping("/displayCustomers")
    public String showCustomers(Model model) {
    	Iterable<UserDTO> customersDTO = null;
    	try {
			customersDTO = userService.findUsersByRole(roleService.findByRoleName("ROLE_USER"));
		} catch (FinderException e) {
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
    	model.addAttribute("customersDTO", customersDTO);
    	return "display-users";
    }
    
}