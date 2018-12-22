package com.nke.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nke.domain.Category;
import com.nke.domain.Product;
import com.nke.domain.repository.CategoryRepository;
import com.nke.domain.repository.ProductRepository;
import com.nke.exception.NoProductFoundException;


@Controller
@RequestMapping("/equipment")
public class EquipmentController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
@RequestMapping("/categories")
public String listProductCategories(@RequestParam("page") int page, Model model){
	
	//Page Number from URL e.g. page=1
	int pagenumber = page;
	model.addAttribute("pagenumber", pagenumber);
	
	//Row count for following calculations
	int maxrows = categoryRepository.getCategoryCountWithProducts();
	
	//Find the Max number of pages and see if an extra page is needed
	if (maxrows >= 8){
		int maxpages = maxrows / 8;
		int pageTest = maxpages * 8; 
		int addPage = maxrows - pageTest;
		int zero = 0;
	//Add an extra page if TRUE
		if (addPage > zero){
			int extraPage = maxpages + 1;
			model.addAttribute("maxpages", extraPage);
	//Do not add an extra page if FALSE		
		}else {
			model.addAttribute("maxpages", maxpages);
		}
	//Only one page is needed because at least 8 rows are not available 
	}else{
		model.addAttribute("maxpages", 1);
	 }
	//pull the first 8 on page 1
	if (pagenumber <= 1) {
		
		List<Category> categories = categoryRepository.getAllCategoriesWithProducts(0, 8);

		model.addAttribute("categories", categories);
		
	}else{
		int stopSQL = 8; //Offset
		int pageNumber = pagenumber - 1; 
		int startSQL = 8 * pageNumber; //What row should the query start at, e.g. 8 * 2 = 16 (start at row 16 and pull the next 8 rows)

		List<Category> categories = categoryRepository.getAllCategoriesWithProducts(startSQL, stopSQL);
		
		model.addAttribute("categories", categories);
		
	}
	
	return "equipmentCategories";
  }

@RequestMapping("/categories/products")
public String listProducts(@RequestParam("page") int page, @RequestParam("category") int categoryId, @ModelAttribute("success") String success, Model model) {
	
	//Page Number from URL e.g. page=1
	int pagenumber = page;
	model.addAttribute("pagenumber", pagenumber);
	model.addAttribute("categoryId", categoryId);
	
	//Row count for following calculations
	int maxrows = productRepository.getProductCount(categoryId);
	
	//Find the Max number of pages and see if an extra page is needed
	if (maxrows >= 8){
		int maxpages = maxrows / 8;
		int pageTest = maxpages * 8; 
		int addPage = maxrows - pageTest;
		int zero = 0;
	//Add an extra page if TRUE
		if (addPage > zero){
			int extraPage = maxpages + 1;
			model.addAttribute("maxpages", extraPage);
	//Do not add an extra page if FALSE		
		}else {
			model.addAttribute("maxpages", maxpages);
		}
	//Only one page is needed because at least 8 rows are not available 
	}else{
		model.addAttribute("maxpages", 1);
	 }
	//pull the first 8 on page 1
	if (pagenumber <= 1) {
		
		List<Product> products = productRepository.getAllProducts(0, 8, categoryId);
			
		for (Product product : products) {
			
			String createdAt = product.getCreatedAt();
			String updatedAt = product.getUpdatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");
			formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = null;
			
			try {
				
				date = formatter.parse(createdAt);
				product.setCreatedAt((formatter2.format(date)));
				date = formatter.parse(updatedAt);
				product.setUpdatedAt((formatter2.format(date)));
				
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		model.addAttribute("products", products);
		
	}else{
		int stopSQL = 8; //Offset
		int pageNumber = pagenumber - 1; 
		int startSQL = 8 * pageNumber; //What row should the query start at, e.g. 8 * 2 = 16 (start at row 16 and pull the next 8 rows)

		List<Product> products = productRepository.getAllProducts(startSQL, stopSQL, categoryId);
		
for (Product product : products) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");
			formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date;
			
			try {
				
				date = formatter.parse(product.getCreatedAt());
				product.setCreatedAt((formatter2.format(date)));
				date = formatter.parse(product.getUpdatedAt());
				product.setUpdatedAt((formatter2.format(date)));
				
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		
		model.addAttribute("products", products);
		
	}
	
	return "equipmentProducts";
	
  }

@RequestMapping ("/categories/products/view")
String getProductById(@RequestParam("id") int productId, @RequestParam("category") String categoryId, @RequestParam(value="page", required=false) String page, Model model) {
	
	List<Product> exists = productRepository.getProductExists(productId);
	
	if(exists== null || exists.isEmpty()) {
		throw new NoProductFoundException();
	}
	
	for (Product product : exists) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");
		formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date;
		
		try {
			
			date = formatter.parse(product.getCreatedAt());
			product.setCreatedAt((formatter2.format(date)));
			date = formatter.parse(product.getUpdatedAt());
			product.setUpdatedAt((formatter2.format(date)));
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	
	 Product product = new Product();
	 product = exists.get(0);
	 
	String productCategory = categoryRepository.getCategoryTitle(product.getCategoryId());
	
	model.addAttribute("productCategory", productCategory);
	model.addAttribute("product", product);
	model.addAttribute("pagenumber", page);
	
	return "productView";
 }

}
