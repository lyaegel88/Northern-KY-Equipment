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

import com.nke.domain.Product;
import com.nke.domain.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository{

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum)
		throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getInt("id"));
			product.setCreatedAt(rs.getString("created_at"));
			product.setUpdatedAt(rs.getString("updated_at"));
			product.setTitle(rs.getString("title"));
			product.setPrice(rs.getString("price"));
			product.setCategoryId(rs.getInt("category_id"));
			product.setPhotoId(rs.getInt("photo_id"));
			product.setDescription(rs.getString("description"));
			product.setPhotoUrl(rs.getString("url"));
			product.setPhotoVersion(rs.getInt("version"));
			product.setPublicId(rs.getString("public_id"));
			
			return product;
		}
	}

	@Override
	public int getProductCount(int categoryId) {
		String SQL = "SELECT COUNT(*) from product WHERE category_id = :category_id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category_id", categoryId);
		Integer result = jdbcTemplate.queryForObject(SQL, params, Integer.class);
		
		return result;
	}

	@Override
	public List<Product> getAllProducts(int start, int stop, int categoryId) {
		String SQL = "SELECT pr.*, p.url, p.version, p.public_id FROM product AS pr INNER JOIN photo AS p ON pr.photo_id = p.id WHERE pr.category_id = :category_id LIMIT :start, :stop";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", start);
		params.put("stop", stop);
		params.put("category_id", categoryId);
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		
		return result;
	}

	@Override
	public void addProduct(Product newProduct) {
		String SQL = "INSERT INTO product (created_at, "
				+ "updated_at,"
				+ "title,"
				+ "price,"
				+ "category_id,"
				+ "photo_id,"
				+ "description)"
				+ "VALUES (:created_at, :updated_at, :title, :price, :category_id, :photo_id, :description)";
		Map<String, Object> params = new HashMap<>();
		params.put("created_at", newProduct.getCreatedAt());
		params.put("updated_at", newProduct.getUpdatedAt());
		params.put("title",newProduct.getTitle());
		params.put("price", newProduct.getPrice());
		params.put("category_id", newProduct.getCategoryId());
		params.put("photo_id", newProduct.getPhotoId());
		params.put("description", newProduct.getDescription());
		
		jdbcTemplate.update(SQL, params);
		
	}

	@Override
	public List<Product> getProductExists(int productId) {
		String SQL = "SELECT pr.*, p.url, p.version, p.public_id FROM product AS pr INNER JOIN photo AS p ON pr.photo_id = p.id WHERE pr.id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productId);
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		
		return result;
	}

	@Override
	public void updateProduct(String updatedDate, String title, String price, String description, int productId) {
		String SQL = "UPDATE product SET updated_at = :updated_at, title = :title, price = :price, description = :description WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("updated_at", updatedDate);
		params.put("title", title);
		params.put("price", price);
		params.put("description", description);
		params.put("id", productId);
		
		jdbcTemplate.update(SQL, params);
		
	}

	@Override
	public void deleteProduct(int productId) {
		String SQL = "DELETE FROM product WHERE id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productId);
		
		jdbcTemplate.update(SQL, params);
		
	}

	@Override
	public List<Product> getProductsByCategory(String categoryId) {
		String SQL = "SELECT pr.*, p.url, p.version, p.public_id FROM product AS pr INNER JOIN photo AS p ON pr.photo_id = p.id WHERE pr.category_id = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", categoryId);
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		
		return result;
	}
	
	@Override
	public List<Product> getRandomProducts() {
		String SQL = "SELECT pr.*, p.url, p.version, p.public_id FROM product AS pr INNER JOIN photo AS p ON pr.photo_id = p.id ORDER BY RAND() LIMIT 3";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		
		return result;
	}
	
	@Override
	public List<Product> getTwoProducts() {
		String SQL = "SELECT pr.*, p.url, p.version, p.public_id FROM product AS pr INNER JOIN photo AS p ON pr.photo_id = p.id ORDER BY pr.created_at DESC LIMIT 2";
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> result = jdbcTemplate.query(SQL, params, new ProductMapper());
		
		return result;
	}
	
}
