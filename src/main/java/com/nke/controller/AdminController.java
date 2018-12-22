package com.nke.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nke.domain.Category;
import com.nke.domain.Photo;
import com.nke.domain.Product;
import com.nke.domain.User;
import com.nke.domain.repository.CategoryRepository;
import com.nke.domain.repository.PhotoRepository;
import com.nke.domain.repository.ProductRepository;
import com.nke.domain.repository.UserRepository;
import com.nke.exception.NoCategoryFoundException;
import com.nke.exception.NoProductFoundException;
import com.nke.exception.NoUserFoundException;
import com.nke.exception.SpringException;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private Cloudinary cloudinary;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
@RequestMapping("")
	public String admin(Model model) {
	
	return "admin";
	}

@RequestMapping ("/category")
public String listCategories(@RequestParam("page") int page, @ModelAttribute("success") String success, Model model) {
	
	//Page Number from URL e.g. page=1
	int pagenumber = page;
	model.addAttribute("pagenumber", pagenumber);
	
	//Row count for following calculations
	int maxrows = categoryRepository.getCategoryCount();
	
	//Find the Max number of pages and see if an extra page is needed
	if (maxrows >= 7){
		int maxpages = maxrows / 7;
		int pageTest = maxpages * 7; 
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
	//Only one page is needed because at least 7 rows are not available 
	}else{
		model.addAttribute("maxpages", 1);
	 }
	//pull the first 7 on page 1
	if (pagenumber <= 1) {
		
		List<Category> categories = categoryRepository.getAllCategories(0, 7);
		
		for (Category category : categories) {
			
			String createdAt = category.getCreatedAt();
			String updatedAt = category.getUpdatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
			formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = null;
			
			try {
				
				date = formatter.parse(createdAt);
				category.setCreatedAt((formatter2.format(date)));
				date = formatter.parse(updatedAt);
				category.setUpdatedAt((formatter2.format(date)));
				
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}

		model.addAttribute("categories", categories);
		
	}else{
		int stopSQL = 7; //Offset
		int pageNumber = pagenumber - 1; 
		int startSQL = 7 * pageNumber; //What row should the query start at, e.g. 7 * 2 = 14 (start at row 14 and pull the next 7 rows)

		List<Category> categories = categoryRepository.getAllCategories(startSQL, stopSQL);
		
		for (Category category : categories) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
			formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date;
			
			try {
				
				date = formatter.parse(category.getCreatedAt());
				category.setCreatedAt((formatter2.format(date)));
				date = formatter.parse(category.getUpdatedAt());
				category.setUpdatedAt((formatter2.format(date)));
				
			} catch (ParseException e) {

				e.printStackTrace();
			}
		}
		
		model.addAttribute("categories", categories);
		
	}
	
		model.addAttribute("success", success);
	
	return "categories";
	
  }

@RequestMapping ("/category/manage")
String getCategoryById(@RequestParam("id") String categoryId, @RequestParam(value="page", required=false) String page, Model model) {
	
	List<Category> exists = categoryRepository.getCategoryExists(categoryId);
	
	if(exists== null || exists.isEmpty()) {
		throw new NoCategoryFoundException();
	}
	
	for (Category category : exists) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
		formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date;
		
		try {
			
			date = formatter.parse(category.getCreatedAt());
			category.setCreatedAt((formatter2.format(date)));
			date = formatter.parse(category.getUpdatedAt());
			category.setUpdatedAt((formatter2.format(date)));
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	
	 Category category = new Category();
	 category = exists.get(0);
	
	model.addAttribute("category", category);
	model.addAttribute("pagenumber", page);
	
	return "categoryManage";
 }

@RequestMapping (value= "/category/add", method = RequestMethod.GET)
public String addNewCategory(@ModelAttribute("success") String success, Model model) {
	Category newCategory = new Category();
	model.addAttribute("newCategory", newCategory);
	model.addAttribute("success", success);
	
	return "addCategory";
 }

@RequestMapping (value = "/category/add", method = RequestMethod.POST)
public RedirectView processNewCategoryForm(@ModelAttribute ("newCategory") Category newCategory, RedirectAttributes redirectAttributes) throws IOException {
	
	Photo photo = new Photo();
	
	MultipartFile categoryImage = newCategory.getCategoryImage();
	
	
	if (categoryImage !=null && !categoryImage.isEmpty()) {
		
		try {
			File convFile = new File(newCategory.getTitle() + ".png");
			
			categoryImage.transferTo(convFile);
			
			Random rnd = new Random();
			int saltNumber = 100000 + rnd.nextInt(900000);
			
			String version = String.valueOf(1);
			String publicId = newCategory.getTitle() + saltNumber + version;
			publicId = publicId.replaceAll("\\s+","");
			
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id", "nke_folder/" + publicId, "format", "png"));
			
			String url = (String)uploadResult.get("secure_url");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = new Date();
			photo.setCreatedAt(dateFormat.format(date));
			photo.setUpdatedAt(dateFormat.format(date));
			photo.setPhotoUrl(url);
			photo.setVersion(1);
			photo.setPublicId(publicId);
			
		} catch (Exception e) {
			throw new RuntimeException(" Image Failed to Save", e);
		}
		
	}else {
		throw new SpringException("No image has been selected. Please add an image when creating a category.");
	}
	
	int photoId = photoRepository.addPhoto(photo);
	
	newCategory.setPhotoId(photoId);
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	Date date = new Date();
	
	newCategory.setCreatedAt(dateFormat.format(date));
	newCategory.setUpdatedAt(dateFormat.format(date));
	
	categoryRepository.addCategory(newCategory);
	
	//Sending Logs to Papertrail Heroku Plugin
			System.out.println("New Category Added with Category ID: " + newCategory.getCategoryId());
			
			redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Category was successfully ADDED</div>");
			
			return new RedirectView("add");
	
}

@RequestMapping (value= "/category/edit", method = RequestMethod.GET)
public String editCategory(@RequestParam("id") String categoryId, @RequestParam(value="page", required=false) String page, @ModelAttribute("success") String success, Model model) {
	
	List<Category> exists = categoryRepository.getCategoryExists(categoryId);
	
	if(exists== null || exists.isEmpty()) {
		throw new NoCategoryFoundException();
	}
	
	Category category = new Category();
	category = exists.get(0);
	model.addAttribute("category", category);
	model.addAttribute("pagenumber", page);
	model.addAttribute("success", success);
	
	return "editCategory";
	
}

@RequestMapping (value= "/category/edit", method = RequestMethod.POST)
public RedirectView processUpdateCategoryForm(@ModelAttribute ("category") Category category, RedirectAttributes redirectAttributes, @RequestParam("id") String categoryId, @RequestParam(value="page", required=false) String page) {

if (!category.getTitle().isEmpty()) {
	MultipartFile categoryImage = category.getCategoryImage();
	
	if (categoryImage !=null && !categoryImage.isEmpty()) {
		
		try {
			
			cloudinary.uploader().destroy("nke_folder/" + category.getPublicId(), ObjectUtils.asMap("invalidate", true));
			
			File convFile = new File(category.getTitle() + ".png");
			
			categoryImage.transferTo(convFile);
			
			int iterateVersion = category.getPhotoVersion() + 1;
			
			Random rnd = new Random();
			int saltNumber = 100000 + rnd.nextInt(900000);
			
			String newVersion = String.valueOf(iterateVersion);
			String publicId = category.getTitle() + saltNumber + newVersion;
			publicId = publicId.replaceAll("\\s+","");
			
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id", "nke_folder/" + publicId, "format", "png"));
			
			String url = (String)uploadResult.get("secure_url");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = new Date();
			String newUpdatedDate = dateFormat.format(date);
			
			photoRepository.updatePhoto(newUpdatedDate, url, iterateVersion, category.getPhotoId(), publicId);
			
			
		} catch (Exception e) {
			
			throw new RuntimeException(" Image Failed to Save", e);
		}
		
	}
}else {
	throw new SpringException("Category Name Cannot be Empty");
}
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	Date date = new Date();
	String newUpdatedDate = dateFormat.format(date);
	
	categoryRepository.updateCategory(newUpdatedDate, category.getTitle(), category.getCategoryId());
	
	//Sending Logs to Papertrail Heroku Plugin
	System.out.println("The following Category was Updated: " + category.getCategoryId());
	
	redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Category was successfully UPDATED</div>");
	
	return new RedirectView("manage?id=" + categoryId + "&page=" + page);
	
	
	
}

@RequestMapping(value = "/category/delete", method = RequestMethod.POST)
public RedirectView deleteCategory(@RequestParam("id") String categoryId, RedirectAttributes ra) {
	
	List<Category> category = categoryRepository.getCategoryExists(categoryId);
	
	List<Product> products = productRepository.getProductsByCategory(categoryId);
	
	Category newCategory = new Category();
	
	newCategory = category.get(0);
	
	try {
		
		for (Product product : products) {
			
			cloudinary.uploader().destroy("nke_folder/" + product.getPublicId(), ObjectUtils.asMap("invalidate", true));
		}
		
		cloudinary.uploader().destroy("nke_folder/" + newCategory.getPublicId(), ObjectUtils.asMap("invalidate", true));
		
	} catch (IOException e) {

		e.printStackTrace();
	};
	
	categoryRepository.deleteCategory(newCategory.getCategoryId());
	
	for (Product product : products) {
		photoRepository.deletePhoto(product.getPhotoId());
		System.out.println("The following PHOTO was Deleted: " + product.getPhotoId());
	}
	
	photoRepository.deletePhoto(newCategory.getPhotoId());
	
	System.out.println("The following PHOTO was Deleted: " + newCategory.getPhotoId());
	
	//Sending Logs to Papertrail Heroku Plugin
			System.out.println("The following CATEGORY was Deleted: " + newCategory.getCategoryId());
			
			for (Product product : products) {
				System.out.println("The following PRODUCT was Deleted: " + product.getProductId());
			}
			
			ra.addFlashAttribute("deleted", "<div class=\"alert alert-success\">Category and Products were successfully DELETED</div>");
			
			return new RedirectView("/admin/category?page=1");
     
}

@RequestMapping ("/categories")
public String listProductCategories(@RequestParam("page") int page, Model model){
	
	//Page Number from URL e.g. page=1
	int pagenumber = page;
	model.addAttribute("pagenumber", pagenumber);
	
	//Row count for following calculations
	int maxrows = categoryRepository.getCategoryCount();
	
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
	//Only one page is needed because at least 7 rows are not available 
	}else{
		model.addAttribute("maxpages", 1);
	 }
	//pull the first 7 on page 1
	if (pagenumber <= 1) {
		
		List<Category> categories = categoryRepository.getAllCategories(0, 8);

		model.addAttribute("categories", categories);
		
	}else{
		int stopSQL = 8; //Offset
		int pageNumber = pagenumber - 1; 
		int startSQL = 8 * pageNumber; //What row should the query start at, e.g. 8 * 2 = 16 (start at row 16 and pull the next 8 rows)

		List<Category> categories = categoryRepository.getAllCategories(startSQL, stopSQL);
		
		model.addAttribute("categories", categories);
		
	}
	
	
	return "productCategories";
	
  }

@RequestMapping ("/categories/products")
public String listProducts(@RequestParam("page") int page, @RequestParam("category") int categoryId, @ModelAttribute("success") String success, Model model) {
	
	//Page Number from URL e.g. page=1
	int pagenumber = page;
	model.addAttribute("pagenumber", pagenumber);
	model.addAttribute("categoryId", categoryId);
	
	//Row count for following calculations
	int maxrows = productRepository.getProductCount(categoryId);
	
	//Find the Max number of pages and see if an extra page is needed
	if (maxrows >= 7){
		int maxpages = maxrows / 7;
		int pageTest = maxpages * 7; 
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
	//Only one page is needed because at least 7 rows are not available 
	}else{
		model.addAttribute("maxpages", 1);
	 }
	//pull the first 7 on page 1
	if (pagenumber <= 1) {
		
		List<Product> products = productRepository.getAllProducts(0, 7, categoryId);
		
		for (Product product : products) {
			
			String createdAt = product.getCreatedAt();
			String updatedAt = product.getUpdatedAt();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
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
		int stopSQL = 7; //Offset
		int pageNumber = pagenumber - 1; 
		int startSQL = 7 * pageNumber; //What row should the query start at, e.g. 7 * 2 = 14 (start at row 14 and pull the next 7 rows)

		List<Product> products = productRepository.getAllProducts(startSQL, stopSQL, categoryId);
		
		for (Product product : products) {
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
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
	
		model.addAttribute("success", success);
	
	return "products";
	
  }

@RequestMapping (value = "/categories/products/add", method = RequestMethod.GET)
public String addNewProduct(@RequestParam("category") int categoryId, @ModelAttribute("success") String success, Model model) {
	
	Product newProduct = new Product();
	
	model.addAttribute("newProduct", newProduct);
	model.addAttribute("categoryId", categoryId);
	model.addAttribute("success", success);
	
	return "addProduct";
}

@RequestMapping (value = "/categories/products/add", method = RequestMethod.POST)
public RedirectView processProductForm(@ModelAttribute ("newProduct") Product newProduct, RedirectAttributes redirectAttributes, @RequestParam("category") int categoryId) {
	
	Photo photo = new Photo();
	
	MultipartFile productImage = newProduct.getProductImage();
	
	
	if (productImage !=null && !productImage.isEmpty()) {
		
		try {
			File convFile = new File(newProduct.getTitle() + ".png");
			
			productImage.transferTo(convFile);
			
			Random rnd = new Random();
			int saltNumber = 100000 + rnd.nextInt(900000);
			
			String version = String.valueOf(1);
			String publicId = newProduct.getTitle() + saltNumber + version;
			publicId = publicId.replaceAll("\\s+","");
			
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id", "nke_folder/" + publicId, "format", "png"));
			
			String url = (String)uploadResult.get("secure_url");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = new Date();
			photo.setCreatedAt(dateFormat.format(date));
			photo.setUpdatedAt(dateFormat.format(date));
			photo.setPhotoUrl(url);
			photo.setVersion(1);
			photo.setPublicId(publicId);
			
		} catch (Exception e) {
			throw new RuntimeException(" Image Failed to Save", e);
		}
		
	}else {
		throw new SpringException("No image has been selected. Please add an image when creating a category.");
	}
	
	int photoId = photoRepository.addPhoto(photo);
	
	newProduct.setPhotoId(photoId);
	newProduct.setCategoryId(categoryId);
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	Date date = new Date();
	
	newProduct.setCreatedAt(dateFormat.format(date));
	newProduct.setUpdatedAt(dateFormat.format(date));
	
	productRepository.addProduct(newProduct);
	
	//Sending Logs to Papertrail Heroku Plugin
			System.out.println("New Product Added with Category ID: " + newProduct.getProductId());
			
			redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Product was successfully ADDED</div>");
			
			return new RedirectView("add?category=" + categoryId);
}

@RequestMapping ("/categories/products/manage")
String getProductById(@RequestParam("id") int productId, @RequestParam("category") String categoryId, @ModelAttribute("success") String success, @RequestParam(value="page", required=false) String page, Model model) {
	
	List<Product> exists = productRepository.getProductExists(productId);
	
	if(exists.isEmpty()) {
		throw new NoProductFoundException();
	}
	
	for (Product product : exists) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
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
	model.addAttribute("success", success);
	
	return "productManage";
 }

@RequestMapping (value= "/categories/products/edit", method = RequestMethod.GET)
public String editProduct(@RequestParam("id") int productId, @RequestParam("category") String categoryId, @RequestParam(value="page", required=false) String page, @ModelAttribute("success") String success, Model model) {
	
	List<Product> exists = productRepository.getProductExists(productId);
	
	if(exists.isEmpty()) {
		throw new NoCategoryFoundException();
	}
	
	Product product = new Product();
	product = exists.get(0);
	
	model.addAttribute("product", product);
	model.addAttribute("categoryId", categoryId);
	model.addAttribute("pagenumber", page);
	model.addAttribute("success", success);
	
	return "editProduct";
	
}

@RequestMapping (value= "/categories/products/edit", method = RequestMethod.POST)
public RedirectView processUpdateProductForm(@ModelAttribute ("product") Product product, RedirectAttributes redirectAttributes,  @RequestParam("id") int productId, @RequestParam("category") String categoryId, @RequestParam(value="page", required=false) String page) {
	
if (!product.getTitle().isEmpty() && !product.getPrice().isEmpty()) {	
	MultipartFile productImage = product.getProductImage();
	
	if (productImage !=null && !productImage.isEmpty()) {
		
		try {
			
			cloudinary.uploader().destroy("nke_folder/" + product.getPublicId(), ObjectUtils.asMap("invalidate", true));
			
			File convFile = new File(product.getTitle() + ".png");
			
			productImage.transferTo(convFile);
			
			int iterateVersion = product.getPhotoVersion() + 1;
			
			Random rnd = new Random();
			int saltNumber = 100000 + rnd.nextInt(900000);
			
			String newVersion = String.valueOf(iterateVersion);
			String publicId = product.getTitle() + saltNumber + newVersion;
			publicId = publicId.replaceAll("\\s+","");
			
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(convFile, ObjectUtils.asMap("public_id", "nke_folder/" + publicId, "format", "png"));
			
			String url = (String)uploadResult.get("secure_url");
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
			Date date = new Date();
			String newUpdatedDate = dateFormat.format(date);
			
			photoRepository.updatePhoto(newUpdatedDate, url, iterateVersion, product.getPhotoId(), publicId);
			
			
		} catch (Exception e) {
			
			throw new RuntimeException(" Image Failed to Save", e);
		}
		
	}
}else {
	throw new SpringException("Product Name and Price Cannot be Empty");
}
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
	Date date = new Date();
	String newUpdatedDate = dateFormat.format(date);
	
	productRepository.updateProduct(newUpdatedDate, product.getTitle(), product.getPrice(), product.getDescription(), product.getProductId());
	
	//Sending Logs to Papertrail Heroku Plugin
	System.out.println("The following Product was Updated: " + product.getProductId());
	
	redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Product was successfully UPDATED</div>");
	
	return new RedirectView("manage?id=" + productId + "&category=" + product.getCategoryId() + "&page=" + page);
	
}

@RequestMapping(value = "/categories/products/delete", method = RequestMethod.POST)
public RedirectView deleteProduct(@RequestParam("id") int productId, @RequestParam("category") int categoryId, RedirectAttributes ra) {
	
	List<Product> products = productRepository.getProductExists(productId);
	
	Product product = new Product();
	
	product = products.get(0);
	
	try {
		
		cloudinary.uploader().destroy("nke_folder/" + product.getPublicId(), ObjectUtils.asMap("invalidate", true));
		
	} catch (IOException e) {

		e.printStackTrace();
	};
	
	productRepository.deleteProduct(product.getProductId());
	
	photoRepository.deletePhoto(product.getPhotoId());
	
	System.out.println("The following PHOTO was Deleted: " + product.getPhotoId());
	
	//Sending Logs to Papertrail Heroku Plugin
			System.out.println("The following PRODUCT was Deleted: " + product.getProductId());
			
			ra.addFlashAttribute("deleted", "<div class=\"alert alert-success\">Product was successfully DELETED</div>");
			
			return new RedirectView("/admin/categories/products?category=" + categoryId + "&page=1");
     
}

@RequestMapping ("/user/manage")
String getUserByUsername(@ModelAttribute("success") String success, Model model) {
	
	User cachedUser = new User();
	
	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if (principal instanceof UserDetails) {
	  String username = ((UserDetails)principal).getUsername();
	  
	  cachedUser.setUsername(username);
	 
	} else {
	  String username = principal.toString();
	  
	  cachedUser.setUsername(username);
	}
	
	List<User> listUser = userRepository.getUserByUsername(cachedUser.getUsername());
	
	for (User user : listUser) {
		
		String createdAt = user.getCreatedAt();
		String updatedAt = user.getUpdatedAt();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss aa");
		formatter.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		formatter2.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date = null;
		
		try {
			
			date = formatter.parse(createdAt);
			user.setCreatedAt((formatter2.format(date)));
			date = formatter.parse(updatedAt);
			user.setUpdatedAt((formatter2.format(date)));
			
		} catch (ParseException e) {

			e.printStackTrace();
		}
	}
	
	User user = new User();
	
	user = listUser.get(0);
	
	model.addAttribute("success", success);
	model.addAttribute("user", user);
	
	return "userManage";
}

@RequestMapping (value= "/user/edit/password", method = RequestMethod.GET)
public String editUserPassword(@RequestParam("username") String username, Model model) {
	
	User user = new User();
	
	model.addAttribute("username", username);
	model.addAttribute("user", user);
	
	return "editPassword";
	
}

@RequestMapping (value= "/user/edit/password", method = RequestMethod.POST)
public RedirectView processUpdateUserPasswordForm(@ModelAttribute ("user") User user, RedirectAttributes redirectAttributes) {
	
if (!user.getCurrentPassword().isEmpty() && !user.getNewPassword().isEmpty()) {	
	List<User> listUser = userRepository.getUserByUsername(user.getUsername());
	
	User compareUser = new User();
	compareUser = listUser.get(0);
	
	BCryptPasswordEncoder passwordEndoderService = new BCryptPasswordEncoder();
	
	boolean passwordsMatch = passwordEndoderService.matches(user.getCurrentPassword(), compareUser.getCurrentPassword());
	
	if (passwordsMatch) {
		
		String newPassword = passwordEndoderService.encode(user.getNewPassword());
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date = new Date();
		String newUpdatedDate = dateFormat.format(date);
		
		userRepository.updateUserPassword(newUpdatedDate, newPassword, compareUser.getUserId());
		
	}else {
		throw new SpringException("The Old Password is Incorrect");
	}
}else {
	
	throw new SpringException("Password Cannot Be Blank");
	
}
	//Sending Logs to Papertrail Heroku Plugin
		System.out.println("The following User's Password was Updated: " + user.getUserId());
		
		redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Your Password was successfully UPDATED</div>");
	
	return new RedirectView("/admin/user/manage");
}

@RequestMapping (value= "/user/edit/email", method = RequestMethod.GET)
public String editUserEmail(@RequestParam("username") String username, Model model) {
	
	List<User> listUser = userRepository.getUserByUsername(username);
	
	if(listUser.isEmpty()) {
		throw new NoUserFoundException();
	}
	
	User user = new User();
	user = listUser.get(0);
	
	user.setCurrentPassword("");
	
	model.addAttribute("user", user);
	
	return "editEmail";
	
}

@RequestMapping (value= "/user/edit/email", method = RequestMethod.POST)
public RedirectView processUpdateUserEmailForm(@ModelAttribute ("user") User user, RedirectAttributes redirectAttributes) {

if (!user.getEmailAddress().isEmpty() && !user.getCurrentPassword().isEmpty()) {
	List<User> listUser = userRepository.getUserByUsername(user.getUsername());
	
	User compareUser = new User();
	compareUser = listUser.get(0);
	
	BCryptPasswordEncoder passwordEndoderService = new BCryptPasswordEncoder();
	
	boolean passwordsMatch = passwordEndoderService.matches(user.getCurrentPassword(), compareUser.getCurrentPassword());
	
	if (passwordsMatch) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		Date date = new Date();
		String newUpdatedDate = dateFormat.format(date);
		
		userRepository.updateUserEmail(newUpdatedDate, user.getEmailAddress(), user.getUserId());
		
	}else {
		throw new SpringException("Your Password is Incorrect");
	}
}else {
	throw new SpringException("Email and Password Cannot be Empty");
}
	//Sending Logs to Papertrail Heroku Plugin
		System.out.println("The following User's Email was Updated: " + user.getUserId());
		
		redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Your Email was successfully UPDATED</div>");
	
	return new RedirectView("/admin/user/manage");
}

@ExceptionHandler({SpringException.class})
public String handleError(Model model, SpringException ex) {
	
	model.addAttribute("exception", ex.getExceptionMsg());
	
	return "ExceptionPage";
}

}
