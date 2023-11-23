package com.yaps.petstore.domain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yaps.petstore.domain.model.Category;
import com.yaps.petstore.domain.model.Item;
import com.yaps.petstore.domain.model.Product;
import com.yaps.petstore.domain.service.CounterService;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

/**
 * This class tests the ItemDAO class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public final class ItemDAOTest {

	private static final String COUNTER_NAME = "Item";
	
	private static Logger logger = LogManager.getLogger(ItemDAOTest.class);
	
	@Autowired
	private ItemRepository ItemRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
    private CounterService counterService;
	
    private final double _defaultUnitCost = 1.2345;

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
    @Test
    public void testDomainFindItemWithInvalidValues() throws Exception {

        // Finds an object with a unknown identifier
        final String id = counterService.getUniqueId(COUNTER_NAME);
        try {
            findItem(id);
            fail("Object with unknonw id should not be found");
        } catch (NoSuchElementException e) {
        }

        // Finds an object with an empty identifier
        try {
            ItemRepository.findById(new String()).get();
            fail("Object with empty id should not be found");
        } catch (Exception e) {
        }

        // Finds an object with a null identifier
        try {
            ItemRepository.findById(null).get();
            fail("Object with null id should not be found");
        } catch (Exception e) {
        }
    }

    /**
     * This test ensures that the method findAll works. It does a first findAll, creates
     * a new object and does a second findAll.
     */
    @Test
    public void testDomainFindAllItems() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // First findAll
        final int firstSize = findAllItems();
        // Create an object
        createItem(id);

        // Ensures that the object exists
        try {
            findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // second findAll
        final int secondSize = findAllItems();

        // Checks that the collection size has increase of one
        if (firstSize + 1 != secondSize) fail("The collection size should have increased by 1");

        // Cleans the test environment
        removeItem(id);

        try {
            findItem(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * This test ensures that the method findAll works. It does a first findAll, creates
     * a new object and does a second findAll.
     */
    @Test
    public void testDomainFindAllItemsForAProduct() throws Exception {

    	Product newProduct = createNewProduct();
    	final String productId = newProduct.getId();

        // First findAll
        final int firstSize = findAllItems(productId);

        // Checks that the collection is empty
        if (firstSize != 0) fail("The collection should be empty");

        // Create an object
        Item item1 = createItemForProduct(newProduct);

        // Ensures that the object exists
        try {
            findItem(item1.getId());
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // second findAll
        final int secondSize = findAllItems(productId);

        // Checks that the collection size has increase of one
        if (firstSize + 1 != secondSize) fail("The collection size should have increased by 1");

        // Create an new object with a different id
        Item item2 = createItemForProduct(newProduct);

        // Ensures that the new object exists
        try {
            findItem(item2.getId());
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // third findAll
        final int thirdSize = findAllItems(productId);

        // Checks that the collection size has increase of one
        if (thirdSize != secondSize + 1) fail("The collection should have the same size");

        // Cleans the test environment
        ItemRepository.delete(item1);
        ItemRepository.delete(item2);
        removeProduct(newProduct);
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
    @Test
    public void testDomainCreateItem() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        Item item = null;

        // Ensures that the object doesn't exist
        try {
            item = findItem(id);
            fail("Object has not been created yet it shouldn't be found");
        } catch (NoSuchElementException e) {
        }

        // Creates an object
        createItem(id);

        // Ensures that the object exists
        try {
            item = findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkItem(item, id);

        // Creates an object with the same identifier. An exception has to be thrown
        // save de CrudRepository ne renvoie pas d'exception. L'objet n'est juste pas créé
//        try {
//            createItem(id);
//            fail("An object with the same id has already been created");
//        } catch (DuplicateKeyException e) {
//        }

        // Cleans the test environment
        removeItem(id);

        try {
            findItem(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
    @Test
    public void testDomainCreateItemWithInvalidValues() throws Exception {

        // Creates an object with an empty values
        try {
            final Item item = new Item(new String(), new String(), 0, null);
            item.checkData();
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with an null values
        try {
            final Item item = new Item(null, null, 0, null);
            item.checkData();
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    /**
     * This test tries to create an object with a invalid linked object.
     */
    @Test
    public void testDomainCreateItemWithInvalidProduct() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Creates an object with a null linked object
        try {
            final Item item = new Item("item" + id, "name" + id, 0, null);
            item.checkData();
            fail("Object with null object linked should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with an empty linked object
        try {
            final Item item = new Item("item" + id, "name" + id, 0, new Product());
            item.checkData();
            fail("Object with an empty object linked should not be created");
        } catch (CheckException e) {
        }
    }

    /**
     * This test tries to update an object with a invalid values.
     */
    @Test
    public void testDomainUpdateItemWithInvalidValues() throws Exception {

        // Creates an object
        final String id = counterService.getUniqueId(COUNTER_NAME);
        createItem(id);

        // Ensures that the object exists
        Item item = null;
        try {
            item = findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // Updates the object with empty values
        try {
            item.setName(new String());
            item.setUnitCost(0);
            item.checkData();
            fail("Updating an object with empty values should break");
        } catch (CheckException e) {
        }

        // Updates the object with null values
        try {
            item.setName(null);
            item.setUnitCost(0);
            item.checkData();
            fail("Updating an object with null values should break");
        } catch (CheckException e) {
        }

        // Ensures that the object still exists
        try {
            item = findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object should be found");
        }

        // Cleans the test environment
        removeItem(id);

        try {
            findItem(id);
            fail();
        } catch (NoSuchElementException e) {
        }
    }

    /**
     * This test make sure that updating an object success
     */
    @Test
    public void testDomainUpdateItem() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Creates an object
        createItem(id);

        // Ensures that the object exists
        Item item = null;
        try {
            item = findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkItem(item, id);

        // Updates the object with new values
        updateItem(item, id + 1);

        // Ensures that the object still exists
        Item itemUpdated = null;
        try {
            itemUpdated = findItem(id);
        } catch (NoSuchElementException e) {
            fail("Object should be found");
        }

        // Checks that the object values have been updated
        checkItem(itemUpdated, id + 1);

        // Cleans the test environment
        removeItem(id);

        try {
            findItem(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (NoSuchElementException e) {
        }
    }

    //==================================
    //=         Private Methods        =
    //==================================
    private Item findItem(final String id) throws NoSuchElementException {
        final Item item = (Item)ItemRepository.findById(id).get();
        return item;
    }

    private int findAllItems() throws FinderException {
        try {
            return ((Collection<Item>) ItemRepository.findAll()).size();
        } catch (Exception e) {
        	logger.info("Exception is ... "+e.getMessage());
            return 0;
        }
    }

    private int findAllItems(String productId) throws FinderException {
        try {
        	Product product = productRepository.findById(productId).get();
            return ((Collection<Item>)ItemRepository.findAllByProduct(product)).size();
        } catch (Exception e) {
            return 0;
        }
    }

    // Creates a category first, then a product and then an item linked to this product
    private void createItem(final String id) throws CreateException, CheckException {
        // Create Category
        final String newCategoryId = counterService.getUniqueId("Category");
        final Category category = new Category("cat" + newCategoryId, "name" + newCategoryId, "description" + newCategoryId);
        categoryRepository.save(category);
        // Create Product
        final String newProductId = counterService.getUniqueId("Product");
        final Product product = new Product("prod" + newProductId, "name" + newProductId, "description" + newProductId, category);
        productRepository.save(product);
        // Create Item
        final Item item = new Item(id, "name" + id, _defaultUnitCost, product);
        item.setImagePath("imagePath" + id);
        ItemRepository.save(item);
    }

    // Creates a category, a product and updates the item with this new product
    private void updateItem(final Item item, final String id) throws UpdateException, CreateException, CheckException, ObjectNotFoundException, RemoveException {
     // get old  product
        Product prod = item.getProduct();
    	
    	// Create Category
        final String newCategoryId = counterService.getUniqueId("Category");
        final Category category = new Category("cat" + newCategoryId, "name" + newCategoryId, "description" + newCategoryId);
        categoryRepository.save(category);
        // Create Product
        final String newProductId = counterService.getUniqueId("Product");
        final Product product = new Product("prod" + newProductId, "name" + newProductId, "description" + newProductId, category);
        productRepository.save(product);        
        
        // Updates the item
        item.setName("name" + id);
        item.setUnitCost(_defaultUnitCost);
        item.setImagePath("imagePath" + id);
        item.setProduct(product);
        ItemRepository.save(item);
     // delete old category &  product
        removeProduct(prod);
    }

    private void checkItem(final Item item, final String id) {
        assertEquals("name", "name" + id, item.getName());
        assertEquals("unitCost", new Double(_defaultUnitCost), new Double(item.getUnitCost()));
        assertNotNull("product", item.getProduct());
        assertEquals("imagePath", "imagePath" + id, item.getImagePath());
        }

    private void removeItem(final String id) throws RemoveException, CheckException, ObjectNotFoundException {
        Item item = ItemRepository.findById(id).get();
        final String productId = item.getProduct().getId();
        Product product = productRepository.findById(productId).get();
        final String categoryId = product.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        ItemRepository.delete(item);
        productRepository.delete(product);
        categoryRepository.delete(category);
    }

    // Creates a category first, then a product and return it
    private Product createNewProduct() throws CreateException, CheckException {
        // Create Category
        final String newCategoryId = counterService.getUniqueId("Category");
        final Category category = new Category("cat" + newCategoryId, "name" + newCategoryId, "description" + newCategoryId);
        categoryRepository.save(category);
        // Create Product
        final String newProductId = counterService.getUniqueId("Product");
        final Product product = new Product("prod" + newProductId, "name" + newProductId, "description" + newProductId, category);
        productRepository.save(product);
        return product;
    }

    // Creates an item linked to an existing product
    private Item createItemForProduct(final Product product) throws CreateException, CheckException {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        final Item item = new Item(id, "name" + id, _defaultUnitCost, product);
        item.setImagePath("imagePath" + id);
        ItemRepository.save(item);
        return item;
    }

    private void removeProduct(final Product product) throws RemoveException, CheckException, ObjectNotFoundException {
        final String categoryId = product.getCategory().getId();
        Category category = categoryRepository.findById(categoryId).get();
        productRepository.delete(product);
        categoryRepository.delete(category);
    }
}
