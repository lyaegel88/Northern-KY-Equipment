package com.nke.domain.repository;

import java.util.List;

import com.nke.domain.Product;

public interface ProductRepository {

	int getProductCount(int categoryId);
	
	List<Product> getAllProducts(int start, int stop, int categoryId);
	
	void addProduct(Product newProduct);
	
	List<Product> getProductExists(int productId);
	
	void updateProduct(String updatedDate, String title, String price, String description, int productId );
	
	void deleteProduct(int productId);
	
	List<Product> getProductsByCategory(String categoryId);

	List<Product> getRandomProducts();

	List<Product> getTwoProducts();
	
}
