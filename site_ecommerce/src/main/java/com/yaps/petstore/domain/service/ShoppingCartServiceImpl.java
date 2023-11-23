package com.yaps.petstore.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.yaps.petstore.domain.dto.ItemDTO;
import com.yaps.petstore.domain.dto.ShoppingCartItemDTO;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.FinderException;


//@Scope("session")
public class ShoppingCartServiceImpl implements ShoppingCartService{
	
	//@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(ShoppingCartServiceImpl.class);
	
	// ======================================
    // =             Attributes             =
    // ======================================
    private Map<String,Integer> _shoppingCart;

    @Autowired
    private CatalogService catalogService;
    
    // ======================================
    // =            Constructor            =
    // ======================================
    public ShoppingCartServiceImpl() {
    	_shoppingCart =  new HashMap<>();
    }

    // ======================================
    // =     Lifecycle Callback methods     =
    // ======================================

    
    public void clear() {
        _shoppingCart = null;
    }

    public Map<String, Integer> getCart() {
        return _shoppingCart;
    }

    public Collection<ShoppingCartItemDTO> getItems() {
        final Collection<ShoppingCartItemDTO> items = new ArrayList<>();

        Iterator<Map.Entry<String, Integer>> it = _shoppingCart.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> keyValue = it.next();
            String itemId = (String)keyValue.getKey();
            int quantity = (Integer)keyValue.getValue();
            final ItemDTO itemDTO;
            try {
            	itemDTO = catalogService.findItem(itemId);
                ShoppingCartItemDTO shoppingCartItemDTO = new ShoppingCartItemDTO(itemId, itemDTO.getName(),
                		itemDTO.getProductDescription(), quantity, itemDTO.getUnitCost());
                items.add(shoppingCartItemDTO);
            } catch (FinderException | CheckException e) {
                logger.warn("ShoppingCart.getItems() " + e + ": " + itemId);
            }
        }
        return items;
    }

    public void addItem(String itemId) {
        _shoppingCart.put(itemId, 1);
    }

    public void removeItem(String itemId) {
        _shoppingCart.remove(itemId);
    }

    public void updateItemQuantity(String itemId, int newQty) {
        removeItem(itemId);
        if (newQty > 0) {
            _shoppingCart.put(itemId, newQty);
        }
    }

    public Double getTotal() {
        double total = 0.0;
        Collection<ShoppingCartItemDTO> cartItems = getItems();
        Iterator<ShoppingCartItemDTO> it = cartItems.iterator();
        while(it.hasNext()) {
            ShoppingCartItemDTO shoppingCartItemDTO = (ShoppingCartItemDTO)it.next();
            total += shoppingCartItemDTO.getTotalCost();
        }
        return total;
    }

    public void empty() {
        _shoppingCart.clear();
    }

}
