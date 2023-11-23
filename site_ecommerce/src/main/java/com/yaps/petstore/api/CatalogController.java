package com.yaps.petstore.api;

import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.yaps.petstore.domain.dto.CategoryDTO;
import com.yaps.petstore.domain.dto.ItemDTO;
import com.yaps.petstore.domain.dto.ProductDTO;
import com.yaps.petstore.domain.service.CatalogService;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;
import com.yaps.petstore.logging.Trace;

@Secured("ROLE_ADMIN")
@Controller
public class CatalogController {

	// Used for logging
	private final transient String _cname = this.getClass().getName();

	protected String getCname() {
		return _cname;
	}

	@SuppressWarnings("unused")
	private static Logger logger = LogManager.getLogger(CatalogController.class);

	@Autowired
	private CatalogService catalogService;

	@GetMapping("/manage-categories")
	public String manageCategories(Model model) {
		final String mname = "manageCategories";
		Trace.entering(getCname(), mname);

		Collection<CategoryDTO> categoriesDTO;

		try {
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
		} catch (FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}

	@GetMapping("/update-category/{categoryId}")
	public String showCategory(@PathVariable String categoryId, Model model) {
		final String mname = "showCategory";
		Trace.entering(getCname(), mname);
		CategoryDTO categoryDTO;
		try {
			categoryDTO = catalogService.findCategory(categoryId);
			model.addAttribute("categoryDTO", categoryDTO);
		} catch (CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "update-category";
	}

	@PostMapping("/update-category")
	public String updateCategory(@ModelAttribute CategoryDTO categoryDTO, Model model) {
		final String mname = "updateCategory";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.updateCategory(categoryDTO);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("categoryUpdated", categoryDTO.getId());
			return "manage-catalog";
		} catch (FinderException | UpdateException | CheckException e) {
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception exc) {
			model.addAttribute("exception", exc.getMessage());
			return "error";
		}
	}

	@GetMapping("/delete-category/{categoryId}")
	public String deleteCategory(@PathVariable String categoryId, Model model) {
		final String mname = "deleteCategory";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.deleteCategory(categoryId);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("categoryDeleted", categoryId);
		} catch (RemoveException | CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}

	@GetMapping("/manage-products/{categoryId}")
	public String manageProducts(@PathVariable String categoryId, Model model) {
		final String mname = "manageProducts";
		Trace.entering(getCname(), mname);
		Iterable<ProductDTO> productsDTO;
		try {
			productsDTO = catalogService.findProducts(categoryId);
			model.addAttribute("products", productsDTO);
		} catch (FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}
	
	@GetMapping("/update-product/{productId}")
	public String showProduct(@PathVariable String productId, Model model) {
		final String mname = "showProduct";
		Trace.entering(getCname(), mname);
		ProductDTO productDTO;
		try {
			productDTO = catalogService.findProduct(productId);
//			logger.info("productDTO's categoryId is ... "+productDTO.getCategoryId());
			model.addAttribute("productDTO", productDTO);
		} catch (CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "update-product";
	}

	@PostMapping("/update-product")
	public String updateProduct(@ModelAttribute ProductDTO productDTO, Model model) {
		final String mname = "updateProduct";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.updateProduct(productDTO);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("productUpdated", productDTO.getId());
			return "manage-catalog";
		} catch (FinderException | UpdateException | CheckException e) {
			model.addAttribute("exception", e.getMessage());
			return "error";
		} catch (Exception exc) {
			model.addAttribute("exception", exc.getMessage());
			return "error";
		}
	}

	@GetMapping("/delete-product/{productId}")
	public String deleteProduct(@PathVariable String productId, Model model) {
		final String mname = "deleteProduct";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.deleteProduct(productId);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("productDeleted", productId);
		} catch (RemoveException | CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}

	@GetMapping("/manage-items/{productId}")
	public String manageItems(@PathVariable String productId, Model model) {
		final String mname = "manageItems";
		Trace.entering(getCname(), mname);
		Iterable<ItemDTO> itemsDTO;
		try {
			itemsDTO = catalogService.findItems(productId);
			model.addAttribute("items", itemsDTO);
		} catch (FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}
	
	@GetMapping("/update-item/{itemId}")
	public String showItem(@PathVariable String itemId, Model model) {
		final String mname = "showItem";
		Trace.entering(getCname(), mname);
		ItemDTO itemDTO;
		try {
			itemDTO = catalogService.findItem(itemId);
			model.addAttribute("itemDTO", itemDTO);
		} catch (CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "update-item";
	}

	@PostMapping("/update-item")
	public String updateItem(@ModelAttribute ItemDTO itemDTO, Model model) {
		final String mname = "updateItem";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.updateItem(itemDTO);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("itemUpdated", itemDTO.getId());
			return "manage-catalog";
		} catch (FinderException | UpdateException | CheckException e) {
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception exc) {
			model.addAttribute("exception", exc.getMessage());
			return "error";
		}
	}

	@GetMapping("/delete-item/{itemId}")
	public String deleteItem(@PathVariable String itemId, Model model) {
		final String mname = "deleteItem";
		Trace.entering(getCname(), mname);
		Collection<CategoryDTO> categoriesDTO;
		try {
			catalogService.deleteItem(itemId);
			categoriesDTO = (Collection<CategoryDTO>) catalogService.findCategories();
			model.addAttribute("categories", categoriesDTO);
			model.addAttribute("itemDeleted", itemId);
		} catch (RemoveException | CheckException | FinderException e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getClass().getName());
			return "error";
		} catch (Exception e) {
			Trace.throwing(getCname(), mname, e);
			model.addAttribute("exception", e.getMessage());
			return "error";
		}
		return "manage-catalog";
	}

}
