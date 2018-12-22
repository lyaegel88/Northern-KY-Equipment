package com.nke.domain.repository;

import java.util.List;

import com.nke.domain.Category;

public interface CategoryRepository {
	
	int getCategoryCount();

	List<Category> getAllCategories(int start, int stop);
	
	List<Category> getCategoryExists(String categoryId);

	void addCategory(Category category);

	void updateCategory(String updatedDate, String title, int categoryId);

	void deleteCategory(int categoryId);

	String getCategoryTitle(int categoryId);
	
	List<Category> getAllCategoriesWithProducts(int start, int stop);
	
	int getCategoryCountWithProducts();

}
