package com.yaps.petstore.domain.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yaps.petstore.domain.model.Category;
import com.yaps.petstore.domain.service.CounterService;
import com.yaps.petstore.exception.*;

/**
 * This class tests the CategoryDAO class
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public final class CategoryDAOTest {

	private static final String COUNTER_NAME = "Category";
	
	@Autowired
    private CategoryRepository categoryRepository;
	
	@Autowired
    CounterService counterService;

    //==================================
    //=            Test cases          =
    //==================================
    /**
     * This test tries to find an object with a invalid identifier.
     */
	@Test
    public void testDomainFindCategoryWithInvalidValues() throws Exception {

        // Finds an object with a unknown identifier
    	final String id = counterService.getUniqueId(COUNTER_NAME);
        try {
            findCategory(id);
            fail("Object with unknown id should not be found");
        } catch (Exception e) {
        }

        // Finds an object with an empty identifier
        try {
            categoryRepository.findById(new String()).get();
            fail("Object with empty id should not be found");
        } catch (Exception e) {
        }

        // Finds an object with a null identifier
        // !!! Id ne doit pas être null dans findById de CrudRepository
//        try {
//            categoryRepository.findById(null).get();
//            fail("Object with null id should not be found");
//        } catch (Exception e) {
//        }
    }

    /**
     * This test ensures that the method findAll works. It does a first findAll, creates
     * a new object and does a second findAll.
     */
	@Test
    public void testDomainFindAllCategories() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // First findAll
        final int firstSize = findAllCategories();

        // Creates an object
        createCategory(id);

        // Ensures that the object exists
        try {
            findCategory(id);
        } catch (Exception e) {
            fail("Object has been created it should be found");
        }

        // Second findAll
        final int secondSize = findAllCategories();

        // Checks that the collection size has increase of one
        if (firstSize + 1 != secondSize) fail("The collection size should have increased by 1");

        // Cleans the test environment
        removeCategory(id);

        try {
            findCategory(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (Exception e) {
        }
    }

    /**
     * This method ensures that creating an object works. It first finds the object,
     * makes sure it doesn't exist, creates it and checks it then exists.
     */
	@Test
    public void testDomainCreateCategory() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);
        Category category = null;

        // Ensures that the object doesn't exist
        try {
            category = findCategory(id);
            fail("Object has not been created yet it shouldn't be found");
        } catch (Exception e) {
        }

        // Creates an object
        createCategory(id);

        // Ensures that the object exists
        try {
            category = findCategory(id);
        } catch (Exception e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkCategory(category, id);

        // Creates an object with the same identifier. An exception has to be thrown
        // save de CrudRepository ne renvoie pas d'exception. L'objet n'est juste pas créé
//        try {
//            createCategory(id);
//            fail("An object with the same id has already been created");
//        } catch (DuplicateKeyException e) {
//        }

        // Cleans the test environment
        removeCategory(id);

        try {
            findCategory(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (Exception e) {
        }
    }

    /**
     * This test tries to create an object with a invalid values.
     */
	@Test
    public void testDomainCreateCategoryWithInvalidValues() throws Exception {

        // Creates an object with empty values
        try {
            final Category category = new Category(new String(), new String(), new String());
            category.checkData();
            fail("Object with empty values should not be created");
        } catch (CheckException e) {
        }

        // Creates an object with null values
        try {
            final Category category = new Category(null, null, null);
            category.checkData();
            fail("Object with null values should not be created");
        } catch (CheckException e) {
        }
    }

    /**
     * This test tries to update an object with a invalid values.
     */
	@Test
    public void testDomainUpdateCategoryWithInvalidValues() throws Exception {

        // Creates an object
        final String id = counterService.getUniqueId(COUNTER_NAME);
        createCategory(id);

        // Ensures that the object exists
        Category category = null;
        try {
            category = findCategory(id);
        } catch (Exception e) {
            fail("Object has been created it should be found");
        }

        // Updates the object with empty values
        try {
            category.setName(new String());
            category.setDescription(new String());
            category.checkData();
            fail("Updating an object with empty values should break");
        } catch (CheckException e) {
        }

        // Updates the object with null values
        try {
            category.setName(null);
            category.setDescription(null);
            category.checkData();
            fail("Updating an object with null values should break");
        } catch (CheckException e) {
        }

        // Ensures that the object still exists
        try {
            category = findCategory(id);
        } catch (Exception e) {
            fail("Object should be found");
        }

        // Cleans the test environment
        removeCategory(id);

        try {
            findCategory(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (Exception e) {
        }
    }

    /**
     * This test make sure that updating an object success
     */
	@Test
    public void testDomainUpdateCategory() throws Exception {
        final String id = counterService.getUniqueId(COUNTER_NAME);

        // Creates an object
        createCategory(id);

        // Ensures that the object exists
        Category category = null;
        try {
            category = findCategory(id);
        } catch (Exception e) {
            fail("Object has been created it should be found");
        }

        // Checks that it's the right object
        checkCategory(category, id);

        // Updates the object with new values
        updateCategory(category, id + 1);

        // Ensures that the object still exists
        Category categoryUpdated = null;
        try {
            categoryUpdated = findCategory(id);
        } catch (Exception e) {
            fail("Object should be found");
        }

        // Checks that the object values have been updated
        checkCategory(categoryUpdated, id + 1);

        // Cleans the test environment
        removeCategory(id);

        try {
            findCategory(id);
            fail("Object has been deleted it shouldn't be found");
        } catch (Exception e) {
        }
    }

    //==================================
    //=         Private Methods        =
    //==================================
    private Category findCategory(final String id) throws Exception {
        final Category category = categoryRepository.findById(id).get();
        return category;
    }

    private int findAllCategories() throws FinderException {
        try {
            return ((Collection<Category>) categoryRepository.findAll()).size();
        } catch (Exception e) {
            return 0;
        }
    }

    private void createCategory(final String id) throws CreateException, CheckException {
        final Category category = new Category(id, "name" + id, "description" + id);
        categoryRepository.save(category);
    }

    private void updateCategory(final Category category, final String id) throws UpdateException, ObjectNotFoundException {
        category.setName("name" + id);
        category.setDescription("description" + id);
        categoryRepository.save(category);
    }

    private void removeCategory(final String id) throws ObjectNotFoundException, RemoveException {
        final Category category = new Category(id);
        categoryRepository.delete(category);
    }

    private void checkCategory(final Category category, final String id) {
        assertEquals("name", "name" + id, category.getName());
        assertEquals("description", "description" + id, category.getDescription());
    }
    
}