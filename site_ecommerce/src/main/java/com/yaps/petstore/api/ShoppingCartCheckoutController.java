package com.yaps.petstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.yaps.petstore.domain.service.OrderService;
import com.yaps.petstore.domain.service.ShoppingCartService;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.logging.Trace;

@Controller
public class ShoppingCartCheckoutController {
	// Used for logging
	private final transient String _cname = this.getClass().getName();

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	OrderService orderService; 
	
	
	@GetMapping("/checkout")
	protected String checkout(Model model, Authentication authentication) {
		final String mname = "checkout";
		Trace.entering(_cname, mname);
		// A user must be authenticated
		if(authentication == null) {
			model.addAttribute("exception", "no authenticated user");
			return "error";
		}
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();		
		Long orderId=null;
		try {
			orderId = orderService.createOrder(username,shoppingCartService.getCart());
			shoppingCartService.empty();
		} catch (CreateException | CheckException e) {
			Trace.throwing(_cname, mname, e);
			model.addAttribute("exception", e.getClass().getName()+" : "+e.getMessage());
			return "error";
		}
		model.addAttribute("orderId", orderId);
		return "checkout";
	}

}
