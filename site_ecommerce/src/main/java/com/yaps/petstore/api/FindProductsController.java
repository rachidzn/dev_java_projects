package com.yaps.petstore.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaps.petstore.domain.dto.ProductDTO;
import com.yaps.petstore.domain.service.CatalogService;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.logging.Trace;

/**
 * This servlet returns the list of all products for a specific category.
 */
@Controller
public class FindProductsController {
	
	// Used for logging
    private final transient String _cname = this.getClass().getName();
    
    protected String getCname() {
        return _cname;
    }
    
    @Autowired
	private CatalogService cs;

    @GetMapping("/findProducts")
    protected String findProducts(Model model,@RequestParam String categoryId) {
        final String mname = "findProducts";
        Trace.entering(getCname(), mname);

        final Iterable<ProductDTO> productsDTO;

        try {
            Trace.finest(getCname(), mname, "Category id=" + categoryId);
            productsDTO = cs.findProducts(categoryId);
            
            model.addAttribute("categoryId", categoryId);
            model.addAttribute("productsDTO", productsDTO);

        } catch (FinderException e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getClass().getName());
			return "error";
        } catch (Exception e) {
            Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }
        return "products";
    }
}
