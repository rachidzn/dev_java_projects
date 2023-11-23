package com.yaps.petstore.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.yaps.petstore.domain.service.OrderService;
import com.yaps.petstore.domain.service.ShoppingCartService;
import com.yaps.petstore.logging.Trace;

@Controller
public class ShoppingCartController {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ShoppingCartController.class);

	// Used for logging
	private final transient String _cname = this.getClass().getName();

	@Autowired
	ShoppingCartService shoppingCartService;
	
	@Autowired
	OrderService orderService; 
	
	@GetMapping("/viewCart")
	protected String viewCart(Model model) {
		final String mname = "viewCart";
		Trace.entering(_cname, mname);
		model.addAttribute("cart", shoppingCartService.getItems());
		model.addAttribute("cartValue", shoppingCartService.getTotal());
		return "cart";
	}

	@GetMapping("/addToCart")
	protected String addItemToCart(Model model, @RequestParam String itemId) {
		final String mname = "addItemToCart";
		Trace.entering(_cname, mname);
		// Adds the itemId into the Shopping Cart
		shoppingCartService.addItem(itemId);
		model.addAttribute("cart", shoppingCartService.getItems());
		model.addAttribute("cartValue", shoppingCartService.getTotal());
		return "cart";
	}
	
	@GetMapping("/removeFromCart")
	protected String removeItemFromCart(Model model, @RequestParam String itemId) {
		final String mname = "removeItemFromCart";
		Trace.entering(_cname, mname);
		// removes the itemId form the Shopping Cart
		shoppingCartService.removeItem(itemId);
		model.addAttribute("cart", shoppingCartService.getItems());
		model.addAttribute("cartValue", shoppingCartService.getTotal());
		return "cart";
	}
	
	@PostMapping("/updateCart")
	protected String updateCart(Model model, @RequestParam String itemId, @RequestParam int quantity) {
		final String mname = "updateCart";
		Trace.entering(_cname, mname);
		// updates the Shopping Cart
		shoppingCartService.updateItemQuantity(itemId, quantity);
		model.addAttribute("cart", shoppingCartService.getItems());
		model.addAttribute("cartValue", shoppingCartService.getTotal());
		return "cart";
	}
	
}
