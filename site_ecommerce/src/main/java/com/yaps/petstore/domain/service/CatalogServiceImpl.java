package com.yaps.petstore.domain.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yaps.petstore.domain.dao.CategoryRepository;
import com.yaps.petstore.domain.dao.ItemRepository;
import com.yaps.petstore.domain.dao.ProductRepository;
import com.yaps.petstore.domain.dto.CategoryDTO;
import com.yaps.petstore.domain.dto.ItemDTO;
import com.yaps.petstore.domain.dto.ProductDTO;
import com.yaps.petstore.domain.model.Category;
import com.yaps.petstore.domain.model.Item;
import com.yaps.petstore.domain.model.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;
import com.yaps.petstore.logging.Trace;

/**
 * This class is a facade for all catalog services.
 */
@Service
public class CatalogServiceImpl  implements CatalogService{

    // ======================================
    // =             Attributes             =
    // ======================================
	
	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(CatalogServiceImpl.class);

	@Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ItemRepository itemRepository;
    
 // Used for logging
    protected final transient String _cname = this.getClass().getName();

    // ======================================
    // =            Constructors            =
    // ======================================
    public CatalogServiceImpl() {}

    // ======================================
    // =      Category Business methods     =
    // ======================================
    @Override
    @Transactional
    public CategoryDTO createCategory(final CategoryDTO categoryDTO) throws CreateException, CheckException {
        final String mname = "createCategory";
        Trace.entering(_cname, mname, categoryDTO);

        if (categoryDTO == null)
            throw new CreateException("Category object is null");
        
        try {
			if(findCategory(categoryDTO.getId())!=null)
				 throw new DuplicateKeyException();
		} catch (FinderException | CheckException e) {}

        // Transforms DTO into domain object
        final Category category = new Category(categoryDTO.getId(), categoryDTO.getName(), categoryDTO.getDescription());

        category.checkData();
        // Creates the object
        categoryRepository.save(category);

        // Transforms domain object into DTO
        final CategoryDTO result = transformCategory2DTO(category);

        Trace.exiting(_cname, mname, result);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public CategoryDTO findCategory(final String categoryId) throws FinderException, CheckException {  //
        final String mname = "findCategory";
        Trace.entering(_cname, mname, categoryId);
        
        checkId(categoryId);
        
        Category category = null;
        if( ! categoryRepository.findById(categoryId).isPresent())
        	throw new FinderException("Category must exist to be found");
        else 
        	category=categoryRepository.findById(categoryId).get();
        
        // Transforms domain object into DTO
        final CategoryDTO categoryDTO = transformCategory2DTO(category);

        Trace.exiting(_cname, mname, categoryDTO);
        return categoryDTO;
    }

    @Override
    @Transactional
    public void deleteCategory(final String categoryId) throws RemoveException, CheckException {
        final String mname = "deleteCategory";
        Trace.entering(_cname, mname, categoryId);
    	checkId(categoryId);
    	Category category=null;
    	if( ! categoryRepository.findById(categoryId).isPresent())
    		throw new RemoveException("Category must exist to be deleted");
        else 
        	category=categoryRepository.findById(categoryId).get();
        // Deletes the object
        categoryRepository.delete(category);
    }

    @Override
    @Transactional
    public void updateCategory(final CategoryDTO categoryDTO) throws UpdateException, CheckException {
        final String mname = "updateCategory";
        Trace.entering(_cname, mname, categoryDTO);

        if (categoryDTO == null)
            throw new UpdateException("Category object is null");

    	checkId(categoryDTO.getId());
        Category category = null;

        // Checks if the object exists
        if( ! categoryRepository.findById(categoryDTO.getId()).isPresent())
        	throw new UpdateException("Category must exist to be updated");
        else 
        	category=categoryRepository.findById(categoryDTO.getId()).get();

        // Transforms DTO into domain object
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        
        category.checkData();
        // Updates the object
        	categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<CategoryDTO> findCategories() throws FinderException {
        final String mname = "findCategories";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Iterable<Category> categories = categoryRepository.findAll();

        // Transforms domain objects into DTOs
        final Iterable<CategoryDTO> categoriesDTO = transformCategories2DTOs(categories);

        Trace.exiting(_cname, mname, new Integer(((Collection<CategoryDTO>) categoriesDTO).size()));
        return categoriesDTO;
    }

    // ======================================
    // =      Product Business methods     =
    // ======================================
    @Override
    @Transactional
    public ProductDTO createProduct(final ProductDTO productDTO) throws CreateException, CheckException {
        final String mname = "createProduct";
        Trace.entering(_cname, mname, productDTO);
        
        if (productDTO == null)
            throw new CreateException("Product object is null");
        
        try {
			if(findProduct(productDTO.getId())!=null)
				 throw new DuplicateKeyException();
		} catch (FinderException  | CheckException e) {}

        try {
			findCategory(productDTO.getCategoryId());
		} catch (CheckException | FinderException e) {
			 throw new CreateException("Category must exist to create a product");
		}
        // Finds the linked object
        Category category = categoryRepository.findById(productDTO.getCategoryId()).get();
       

        // Transforms DTO into domain object
        final Product product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getDescription(), category);

        product.checkData();
        // Creates the object
        productRepository.save(product);

        // Transforms domain object into DTO
        final ProductDTO result = transformProduct2DTO(product);

        Trace.exiting(_cname, mname, result);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public ProductDTO findProduct(final String productId) throws FinderException, CheckException {  //
        final String mname = "findProduct";
        Trace.entering(_cname, mname, productId);

    	checkId(productId);
        
        Product product = null;
        if( ! productRepository.findById(productId).isPresent())
        	throw new FinderException("product must exist to be found");
        else 
        	product=productRepository.findById(productId).get();

        // Transforms domain object into DTO
        final ProductDTO productDTO = transformProduct2DTO(product);

        Trace.exiting(_cname, mname, productDTO);
        return productDTO;
    }

    @Override
    @Transactional
    public void deleteProduct(final String productId) throws RemoveException, CheckException {
        final String mname = "deleteProduct";
        Trace.entering(_cname, mname, productId);
        Product product=null;
    	checkId(productId);
    	if( ! productRepository.findById(productId).isPresent())
    		throw new RemoveException("Product must exist to be deleted");
        else 
        	product=productRepository.findById(productId).get();
       	productRepository.delete(product);
    }

    @Override
    @Transactional
    public void updateProduct(final ProductDTO productDTO) throws UpdateException, CheckException {
        final String mname = "updateProduct";
        Trace.entering(_cname, mname, productDTO);

        if (productDTO == null)
            throw new UpdateException("Product object is null");

        if (productDTO.getCategoryId() == null)
            throw new CheckException("Invalid Category");
    	checkId(productDTO.getId());
    	
        Product product = null;

        // Checks if the object exists
        if( ! productRepository.findById(productDTO.getId()).isPresent())
        	throw new UpdateException("Product must exist to be updated");
        else 
        	product = productRepository.findById(productDTO.getId()).get();

        // Finds the linked object
        Category category = null;
        if( ! categoryRepository.findById(productDTO.getCategoryId()).isPresent())
        	throw new UpdateException("Category must exist to update a product");
        else 
        	category=categoryRepository.findById(productDTO.getCategoryId()).get();

        // Transforms DTO into domain object
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setCategory(category);
		
		product.checkData();
        // Updates the object
        productRepository.save(product);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<ProductDTO> findProducts() throws FinderException {
        final String mname = "findProducts";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Iterable<Product> products = productRepository.findAll();

        // Transforms domain objects into DTOs
        Iterable<ProductDTO> productsDTO = transformProducts2DTOs(products);

        Trace.exiting(_cname, mname, new Integer(((Collection<ProductDTO>) productsDTO).size()));
        return productsDTO;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Iterable<ProductDTO> findProducts(String categoryId) throws FinderException {
    	final String mname = "findProductsByCategory";
        Trace.entering(_cname, mname);

        Category category = null;
        if( ! categoryRepository.findById(categoryId).isPresent())
        	throw new FinderException("Category must exist to be found");
        else 
        	category=categoryRepository.findById(categoryId).get();
      
        // Finds all the objects
        final Iterable<Product> productsByCategory = productRepository.findAllByCategory(category);

        // Transforms domain objects into DTOs
        Iterable<ProductDTO> productsDTOByCategory = transformProducts2DTOs(productsByCategory);

        Trace.exiting(_cname, mname, new Integer(((Collection<ProductDTO>) productsDTOByCategory).size()));
        return productsDTOByCategory;
    }

    // ======================================
    // =        Item Business methods       =
    // ======================================
    @Override
    @Transactional
    public ItemDTO createItem(final ItemDTO itemDTO) throws CreateException, CheckException {
        final String mname = "createItem";
        Trace.entering(_cname, mname, itemDTO);

        if (itemDTO == null)
            throw new CreateException("Item object is null");

        if (itemDTO.getProductId() == null)
            throw new CheckException("Invalid Product id");
        
        try {
			findProduct(itemDTO.getProductId());
		} catch (CheckException | FinderException e) {
			 throw new CreateException("Product must exist to create an Item");
		}
        
        try {
			if(findItem(itemDTO.getId())!=null)
				 throw new DuplicateKeyException();
		} catch (FinderException | CheckException  e) {}

        // Finds the linked object
        Product product = null;
        if( ! productRepository.findById(itemDTO.getProductId()).isPresent())
        	throw new CreateException("Product must exist to create an item");
        else 
        	product = productRepository.findById(itemDTO.getProductId()).get();

        // Transforms DTO into domain object
        final Item item = new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getUnitCost(), product);

     // Let's not forget the new attribute !!!
        item.setImagePath(itemDTO.getImagePath());
        
        item.checkData();

        // Creates the object
        itemRepository.save(item);

        // Transforms domain object into DTO
        final ItemDTO result = transformItem2DTO(item);

        Trace.exiting(_cname, mname, result);
        return result;
    }

    @Override
    @Transactional(readOnly=true)
    public ItemDTO findItem(final String itemId) throws FinderException, CheckException {
        final String mname = "findItem";
        Trace.entering(_cname, mname, itemId);
        
        checkId(itemId);
        
        Item item = null;
        if( ! itemRepository.findById(itemId).isPresent())
        	throw new FinderException("Item must exist to be found");
        else 
        	item=itemRepository.findById(itemId).get();

        // Transforms domain object into DTO
        final ItemDTO itemDTO = transformItem2DTO(item);

        Trace.exiting(_cname, mname, itemDTO);
        return itemDTO;
    }

    @Override
    @Transactional
    public void deleteItem(final String itemId) throws RemoveException, CheckException {
        final String mname = "deleteItem";
        Trace.entering(_cname, mname, itemId);
        Item item = null;
    	checkId(itemId);
    	 if( ! itemRepository.findById(itemId).isPresent())
     		throw new RemoveException("Item must exist to be deleted");
         else 
        	 item=itemRepository.findById(itemId).get();
        itemRepository.delete(item);       
    }

    @Override
    @Transactional
    public void updateItem(final ItemDTO itemDTO) throws UpdateException, CheckException {
        final String mname = "updateItem";
        Trace.entering(_cname, mname, itemDTO);

        if (itemDTO == null)
            throw new UpdateException("Item object is null");

        if (itemDTO.getProductId() == null)
            throw new CheckException("Invalid Product in in Item");

        Item item = null;
        // Checks if the object exists
        if( ! itemRepository.findById(itemDTO.getId()).isPresent())
        	throw new UpdateException("Item must exist to be updated");
        else 
        	item = itemRepository.findById(itemDTO.getId()).get();

        // Finds the linked object
        Product product = null;
        if( ! productRepository.findById(itemDTO.getProductId()).isPresent())
        	throw new UpdateException("Product must exist to update an item");
        else 
        	product = productRepository.findById(itemDTO.getProductId()).get();

        // Transforms DTO into domain object
        item.setName(itemDTO.getName());
        item.setUnitCost(itemDTO.getUnitCost());
        item.setProduct(product);
     // ... let's forget the new attribute !!!
        item.setImagePath(itemDTO.getImagePath());

        item.checkData();

        // Updates the object
        itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly=true)
    public Iterable<ItemDTO> findItems() throws FinderException {
        final String mname = "findItems";
        Trace.entering(_cname, mname);

        // Finds all the objects
        final Iterable<Item> items = itemRepository.findAll();

        // Transforms domain objects into DTOs
        final Iterable<ItemDTO> itemsDTO = transformItems2DTOs(items);

        Trace.exiting(_cname, mname, new Integer(((Collection<ItemDTO>) itemsDTO).size()));
        return itemsDTO;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Iterable<ItemDTO> findItems(String productId) throws FinderException {
    	final String mname = "findItemsByProduct";
        Trace.entering(_cname, mname);
        
        Product product = null;
        if( ! productRepository.findById(productId).isPresent())
        	throw new FinderException("Product must exist to be found");
        else 
        	product = productRepository.findById(productId).get();

        // Finds all the objects
        final Iterable<Item> itemsByProduct = itemRepository.findAllByProduct(product);

        // Transforms domain objects into DTOs
        Iterable<ItemDTO> itemsDTOByProduct = transformItems2DTOs(itemsByProduct);

        Trace.exiting(_cname, mname, new Integer(((Collection<ItemDTO>) itemsDTOByProduct).size()));
        return itemsDTOByProduct;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Iterable<ItemDTO> searchItems(String keyword) {
    	final String mname = "searchItems";
        Trace.entering(_cname, mname);
        
     // Finds all the objects
        Iterable<Item> itemsSearchedFor = itemRepository.findByIdOrNameContaining(keyword);    
        
     // Transforms domain objects into DTOs
        Iterable<ItemDTO> itemsDTOSearchedFor = transformItems2DTOs(itemsSearchedFor);

        Trace.exiting(_cname, mname, new Integer(((Collection<ItemDTO>) itemsDTOSearchedFor).size()));
        return itemsDTOSearchedFor;
    }
    
    @Override
    @Transactional(readOnly=true)
    public Iterable<ItemDTO> searchItemsByPrice(String gtLt, Double price) {
    	final String mname = "searchItemsByPrice";
        Trace.entering(_cname, mname);
        Iterable<Item> itemsSearchedFor = null;
        
     // Finds all the objects
        if(gtLt.equals("GT"))
        	itemsSearchedFor = itemRepository.findByUnitCostGreaterThanEqual(price);
        else if(gtLt.equals("LT"))
        	itemsSearchedFor = itemRepository.findByUnitCostLessThanEqual(price);
        else if(gtLt.equals("EQ"))
        	itemsSearchedFor = itemRepository.findByUnitCostEquals(price);
        
     // Transforms domain objects into DTOs
        Iterable<ItemDTO> itemsDTOSearchedFor = transformItems2DTOs(itemsSearchedFor);

        Trace.exiting(_cname, mname, new Integer(((Collection<ItemDTO>) itemsDTOSearchedFor).size()));
        return itemsDTOSearchedFor;
    }
    
	@Override
	@Transactional(readOnly=true)
	public Iterable<ItemDTO> searchItemsByPriceAndKeyword(String gtLt, double price, String keyword) {
		final String mname = "searchItemsByPriceAndKeyword";
        Trace.entering(_cname, mname);
        Iterable<Item> itemsSearchedFor = null;
        
     // Finds all the objects
        if(gtLt.equals("GT"))
        	itemsSearchedFor = itemRepository. findByPriceGreaterThanAndIdOrNameContaining(price,keyword);
        else if(gtLt.equals("LT"))
        	itemsSearchedFor = itemRepository.findByPriceLessThanAndIdOrNameContaining(price,keyword);
        else if(gtLt.equals("EQ"))
        	itemsSearchedFor = itemRepository.findByPriceEqualsAndIdOrNameContaining(price,keyword);
           
     // Transforms domain objects into DTOs
        Iterable<ItemDTO> itemsDTOSearchedFor = transformItems2DTOs(itemsSearchedFor);

        Trace.exiting(_cname, mname, new Integer(((Collection<ItemDTO>) itemsDTOSearchedFor).size()));
        return itemsDTOSearchedFor;
	}

    // ======================================
    // =          Private Methods           =
    // ======================================
    // Category
    private CategoryDTO transformCategory2DTO(final Category category) {
        final CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        categoryDTO.setDescription(category.getDescription());
        return categoryDTO;
    }

    private Iterable<CategoryDTO> transformCategories2DTOs(final Iterable<Category> categories) {
        final Collection<CategoryDTO> categoriesDTO = new ArrayList<>();
        for (Iterator<Category> iterator = categories.iterator(); iterator.hasNext();) {
            final Category category = (Category) iterator.next();
            categoriesDTO.add(transformCategory2DTO(category));
        }
        return categoriesDTO;
    }

    // Product
    private ProductDTO transformProduct2DTO(final Product product) {
        final ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        // Retrieves the data for the linked object
        // Finds the linked object
        Category category = null;
        category = categoryRepository.findById(product.getCategory().getId()).get();
        productDTO.setCategoryId(category.getId());
        productDTO.setCategoryName(category.getName());
        return productDTO;
    }

    private Iterable<ProductDTO> transformProducts2DTOs(final Iterable<Product> products) {
        final Collection<ProductDTO> productsDTO = new ArrayList<>();
        for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
            final Product product = (Product) iterator.next();
            productsDTO.add(transformProduct2DTO(product));
        }
        return productsDTO;
    }

    // Item
    private ItemDTO transformItem2DTO(final Item item) {
        final ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setUnitCost(item.getUnitCost());
     // new attribute not to be forgotten !!!
        itemDTO.setImagePath(item.getImagePath());
        // Retrieves the data for the linked object
        Product product = null;
        product = (Product)productRepository.findById(item.getProduct().getId()).get();
        itemDTO.setProductId(product.getId());
        itemDTO.setProductName(product.getName());
     // ... and product description
        itemDTO.setProductDescription(product.getDescription());
        return itemDTO;
    }

    private Iterable<ItemDTO> transformItems2DTOs(final Iterable<Item> items) {
        final Collection<ItemDTO> itemsDTO = new ArrayList<>();
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
            final Item item = (Item) iterator.next();
            itemsDTO.add(transformItem2DTO(item));
        }
        return itemsDTO;
    }
    
    private void checkId(final String id) throws CheckException {
    	if ( id == null || id.equals("") )
    		throw new CheckException("Id should not be null or empty");    	
    }
}
