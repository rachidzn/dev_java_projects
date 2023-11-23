package com.yaps.petstore.api;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yaps.petstore.domain.dto.ItemDTO;
import com.yaps.petstore.domain.dto.ProductDTO;
import com.yaps.petstore.domain.service.CatalogService;
import com.yaps.petstore.domain.service.CatalogServiceImpl;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.logging.Trace;

/**
 * These servlets returns the selected item / items.
 */
@Controller
public class FindItemController {
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(CatalogServiceImpl.class); 
	
	// Used for logging
    private final transient String _cname = this.getClass().getName();
    
    protected String getCname() {
        return _cname;
    }
    
    @Autowired
	private CatalogService cs;

    @GetMapping("/findItem")
protected String findItem(Model model, @RequestParam String itemId, Authentication authentication) {
        final String mname = "findItem";
        Trace.entering(getCname(), mname);
        final ItemDTO itemDTO;
        try {
            Trace.finest(getCname(), mname, "Item id=" + itemId);
            itemDTO = cs.findItem(itemId);
            if(authentication != null) {
    			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			model.addAttribute("username", userDetails.getUsername());
    		}
            model.addAttribute("itemDTO", itemDTO);
            return "item";
        } catch (FinderException e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getClass().getName());
			return "error";
        } catch (Exception e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }     
    }
    
    @GetMapping("/findItems")
    protected String findItems(Model model, @RequestParam String productId, Authentication authentication) {
        final String mname = "findItems";
        Trace.entering(getCname(), mname);// A user must be authenticated
        final Iterable<ItemDTO> itemsDTO;
        final ProductDTO productDTO;
        try {
            Trace.finest(getCname(), mname, "Product id=" + productId);
            itemsDTO = cs.findItems(productId);         
            productDTO = cs.findProduct(productId);
            if(authentication != null) {
            	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			model.addAttribute("username", userDetails.getUsername());
    		}
            model.addAttribute("productDTO", productDTO);
            model.addAttribute("itemsDTO", itemsDTO);
            return "items";
        } catch (FinderException e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getClass().getName());
			return "error";
        } catch (Exception e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }
    }
    
    @PostMapping("/searchItems")
    protected String searchItems(Model model, @RequestParam String keyword, Authentication authentication) {
        final String mname = "searchItems";
        Trace.entering(getCname(), mname);
        final Iterable<ItemDTO> itemsDTO;
        try {
            Trace.finest(getCname(), mname, "keyword=" + keyword);
            itemsDTO = cs.searchItems(keyword);
            if(authentication != null) {
            	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			model.addAttribute("username", userDetails.getUsername());
    		}
            model.addAttribute("keyword", keyword);
            model.addAttribute("itemsDTO", itemsDTO);
            return "items";
        } catch (Exception e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }
    }
    
    @GetMapping("/price-search")
    protected String priceSearchForm() {
       return "search-by-price";
    }
    
    @PostMapping("/price-search")
    protected String priceSearch(Model model, @RequestParam String gtLt, @RequestParam double price, Authentication authentication) {
        final String mname = "priceSearch";
//        logger.info("price is ... "+price+" ... and comp. is ..."+gtLt);
        Trace.entering(getCname(), mname);
        final Collection<ItemDTO> itemsDTO;
        try {
            Trace.finest(getCname(), mname, "price=" + price);
            itemsDTO = (Collection<ItemDTO>)cs.searchItemsByPrice(gtLt, price);
            if(authentication != null) {
            	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			model.addAttribute("username", userDetails.getUsername());
    		}
            model.addAttribute("price", price);
            model.addAttribute("gtLt", gtLt);
            model.addAttribute("itemsDTO", itemsDTO);
            return "items";
        } catch (Exception e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }
    }
    
    @PostMapping("/price-and-keyword-search")
    protected String priceAndKeywordSearch(Model model, @RequestParam String gtLt, @RequestParam double price, @RequestParam String keyword, Authentication authentication) {
        final String mname = "priceSearch";
        Trace.entering(getCname(), mname);
        final Collection<ItemDTO> itemsDTO;
        try {
            Trace.finest(getCname(), mname, "price=" + price);
            itemsDTO = (Collection<ItemDTO>)cs.searchItemsByPriceAndKeyword(gtLt, price, keyword);
            if(authentication != null) {
            	UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    			model.addAttribute("username", userDetails.getUsername());
    		}
            model.addAttribute("price", price);
            model.addAttribute("gtLt", gtLt);
            model.addAttribute("itemsDTO", itemsDTO);
            model.addAttribute("keyword", keyword);
            return "items";
        } catch (Exception e) {
        	Trace.throwing(getCname(), mname, e);
            model.addAttribute("exception", e.getMessage());
			return "error";
        }
    }
    
    
}
