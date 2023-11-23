package com.yaps.petstore.domain.service;

import com.yaps.petstore.domain.dto.CategoryDTO;
import com.yaps.petstore.domain.dto.ItemDTO;
import com.yaps.petstore.domain.dto.ProductDTO;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;


public interface CatalogService  {
	// ======================================
    // =      Category Business methods     =
    // ======================================
	public CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CreateException, CheckException ;
    public CategoryDTO findCategory(final String categoryId) throws FinderException, CheckException ;
    public void deleteCategory(final String categoryId) throws RemoveException, CheckException ;
    public void updateCategory(final CategoryDTO categoryDTO) throws UpdateException, CheckException ;
    public Iterable<CategoryDTO> findCategories() throws FinderException ;

    // ======================================
    // =      Product Business methods     =
    // ======================================
    public ProductDTO createProduct(final ProductDTO productDTO) throws CreateException, CheckException ;
    public ProductDTO findProduct(final String productId) throws FinderException, CheckException ;
    public void deleteProduct(final String productId) throws RemoveException, CheckException ;
    public void updateProduct(final ProductDTO productDTO) throws UpdateException, CheckException ;
    public Iterable<ProductDTO> findProducts() throws FinderException ;
    public Iterable<ProductDTO> findProducts(String categoryId) throws FinderException;

    // ======================================
    // =        Item Business methods       =
    // ======================================
    public ItemDTO createItem(final ItemDTO itemDTO) throws CreateException, CheckException ;
    public ItemDTO findItem(final String itemId) throws FinderException, CheckException ;
    public void deleteItem(final String itemId) throws RemoveException, CheckException;
    public void updateItem(final ItemDTO itemDTO) throws UpdateException, CheckException ;
    public Iterable<ItemDTO> findItems() throws FinderException ;
    public Iterable<ItemDTO> findItems(String productId) throws FinderException ;
    public Iterable<ItemDTO> searchItems(String keyword);
    public Iterable<ItemDTO> searchItemsByPrice(String gtLt, Double price);
	public Iterable<ItemDTO> searchItemsByPriceAndKeyword(String gtLt, double price, String keyword);

}
