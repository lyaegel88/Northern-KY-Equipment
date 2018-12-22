package com.nke.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nke.domain.Category;
import com.nke.domain.repository.CategoryRepository;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public int getCategoryCount() {
		String SQL = "SELECT COUNT(*) from category";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer result = jdbcTemplate.queryForObject(SQL, params, Integer.class);
		
		return result;
	}

	@Override
	public List<Category> getAllCategories(int start, int stop) {
		String SQL = "SELECT c.*, p.url, p.version, p.public_id FROM category AS c INNER JOIN photo AS p ON c.photo_id = p.id LIMIT :start, :stop";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("stop", stop);
		List<Category> result = jdbcTemplate.query(SQL, params, new CategoryMapper());
		
		return result;
	}
	
	public static final class CategoryMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum)
		throws SQLException {
			Category category = new Category();
			category.setCategoryId(rs.getInt("id"));
			category.setCreatedAt(rs.getString("created_at"));
			category.setUpdatedAt(rs.getString("updated_at"));
			category.setTitle(rs.getString("title"));
			category.setPhotoId(rs.getInt("photo_id"));
			category.setPhotoUrl(rs.getString("url"));
			category.setPhotoVersion(rs.getInt("version"));
			category.setPublicId(rs.getString("public_id"));
			
			return category;
		}
		
	}

	@Override
	public List<Category> getCategoryExists(String categoryId) {
		String SQL = "SELECT c.*, p.url, p.version, p.public_id FROM category AS c INNER JOIN photo AS p ON c.photo_id = p.id WHERE c.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", categoryId);
		List<Category> result = jdbcTemplate.query(SQL, params, new CategoryMapper());
		
		return result;
	}
	
	@Override
	public void addCategory(Category category) {
		String SQL = "INSERT INTO category (created_at, "
				+ "updated_at,"
				+ "title,"
				+ "photo_id)"
				+ "VALUES (:created_at, :updated_at, :title, :photo_id)";
		Map<String, Object> params = new HashMap<>();
		params.put("created_at", category.getCreatedAt());
		params.put("updated_at", category.getUpdatedAt());
		params.put("title",category.getTitle());
		params.put("photo_id", category.getPhotoId());
		
		jdbcTemplate.update(SQL, params);
		
	}
	
	@Override
	public void updateCategory(String updatedDate, String title, int categoryId) {
		String SQL = "UPDATE category SET updated_at = :updated_at, title = :title WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("updated_at", updatedDate);
		params.put("title", title);
		params.put("id", categoryId);
		
		jdbcTemplate.update(SQL, params);
		
		
	}
	
	@Override
	public void deleteCategory(int categoryId) {
		String SQL = "DELETE FROM product WHERE category_id = :id";
		String SQL2 = "DELETE FROM category WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", categoryId);
		
		jdbcTemplate.update(SQL, params);
		
		jdbcTemplate.update(SQL2, params);
		
		
	}
	
	
	@Override
	public String getCategoryTitle(int categoryId) {
		String SQL = "SELECT title FROM category WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", categoryId);
		String result = jdbcTemplate.queryForObject(SQL, params, String.class);
		
		return result;	
		
	}
	
	@Override
	public List<Category> getAllCategoriesWithProducts(int start, int stop) {
		String SQL = "SELECT c.*, p.url, p.version, p.public_id FROM category " 
				+ "AS c INNER JOIN photo AS p " 
				+ "ON c.photo_id = p.id "
				+ "INNER JOIN product AS pr "
				+ "ON c.id = pr.category_id "
				+ "GROUP BY c.id, p.url, p.version, p.public_id "
			    + "LIMIT :start, :stop";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("stop", stop);
		List<Category> result = jdbcTemplate.query(SQL, params, new CategoryMapper());
		
		return result;
	}
	
	@Override
	public int getCategoryCountWithProducts() {
		String SQL = "SELECT COUNT(DISTINCT c.id) from category AS c "
				+ "INNER JOIN product AS pr "
				+ "ON c.id = pr.category_id";
		Map<String, Object> params = new HashMap<String, Object>();
		Integer result = jdbcTemplate.queryForObject(SQL, params, Integer.class);
		
		return result;
	}
	
}
