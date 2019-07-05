package app.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.bean.ProductInfo;
import app.model.Category;
import app.model.Product;
import app.service.CategoryService;
import app.service.ProductService;

@Controller
@PropertySource("classpath:constants.properties")
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;  
	@Autowired
	private CategoryService categoryService;
	
	@Value("${defaultProductPage}")
	private int defaultPage;
	@Value("${defaultProductPageSize}")
	private int defaultPageSize;
	@Value("${defaultProductName}")
	private String defaultProductName;
	@Value("${defaultCategory}")
	private int defaultCategory;
	@Value("${defaultOrderBy}")
	private int defaultOrderBy;
	
	@GetMapping(value = "/index")
	public String index(Model model, @RequestParam("category") Optional<Integer> category,
			@RequestParam("search") Optional<String> search, 
			@RequestParam("orderBy") Optional<Integer> orderBy,
			@RequestParam("page") Optional<Integer> page, 
			@RequestParam("size") Optional<Integer> size) {
		
		List<Category> categories = categoryService.loadSubCategories(0);
		model.addAttribute("categories", categories);
		
		int categoryId = category.orElse(defaultCategory);
		String productName = search.orElse(defaultProductName);
		int order = orderBy.orElse(defaultOrderBy);
		int currentPage = page.orElse(defaultPage);
	    int pageSize = size.orElse(defaultPageSize);
	    
	    
	    Page<Product> products = productService.loadProducts(categoryId, productName, order, PageRequest.of(currentPage - 1, pageSize));
	    
	    model.addAttribute("search", productName);
	    model.addAttribute("products", products);
	    
	    int totalPages = products.getTotalPages();
        model.addAttribute("totalPages", totalPages);
	    return "products/index";
	}
	
	@GetMapping(value = "/{id}")
	public String show(@PathVariable("id") int id, Model model) {
		ProductInfo product = new ProductInfo(productService.findById(id));
		model.addAttribute("product", product);
		return "products/product";
	}
	
}
