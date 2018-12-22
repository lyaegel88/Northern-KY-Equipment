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

import com.nke.domain.Photo;
import com.nke.domain.repository.PhotoRepository;

@Repository
public class PhotoRepositoryImpl implements PhotoRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final class PhotoMapper implements RowMapper<Photo> {
		public Photo mapRow(ResultSet rs, int rowNum)
		throws SQLException {
			Photo photo = new Photo();
			photo.setPhotoId(rs.getInt("id"));
			photo.setCreatedAt(rs.getString("created_at"));
			photo.setUpdatedAt(rs.getString("updated_at"));
			photo.setPhotoUrl(rs.getString("url"));
			photo.setVersion(rs.getInt("version"));
			photo.setPublicId(rs.getString("public_id"));
			
			return photo;
		}
		
	}
	
@Override
	public int addPhoto(Photo photo) {
		String SQL = "INSERT INTO photo (created_at, "
				+ "updated_at,"
				+ "url,"
				+ "version,"
				+"public_id)"
				+ "VALUES (:created_at, :updated_at, :url, :version, :public_id)";
		Map<String, Object> params = new HashMap<>();
		params.put("created_at", photo.getCreatedAt());
		params.put("updated_at", photo.getUpdatedAt());
		params.put("url",photo.getPhotoUrl());
		params.put("version", photo.getVersion());
		params.put("public_id", photo.getPublicId());
		
		jdbcTemplate.update(SQL, params);
		
		String SQL2 = "SELECT id from photo order by id desc limit 1";
		Map<String, Object> params2 = new HashMap<String, Object>();
		Integer result = jdbcTemplate.queryForObject(SQL2, params2, Integer.class);
		
		return result;
	}

@Override
public List<Photo> getPhotoExists(String photoId) {
	String SQL = "SELECT * FROM photo WHERE id = :id";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("id", photoId);
	List<Photo> result = jdbcTemplate.query(SQL, params, new PhotoMapper());
	
	return result;
}


@Override
public void updatePhoto(String updatedDate, String url, int version, int photoId, String publicId) {
	String SQL = "UPDATE photo SET updated_at = :updated_at, url = :url, version = :version, public_id = :public_id WHERE id = :id";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("updated_at", updatedDate);
	params.put("url", url);
	params.put("version", version);
	params.put("id", photoId);
	params.put("public_id", publicId);
	
	jdbcTemplate.update(SQL, params);
	
	
}

@Override
public void deletePhoto(int photoId) {
	String SQL = "DELETE FROM photo WHERE id = :id";
	Map<String, Object> params = new HashMap<String, Object>();
	params.put("id", photoId);
	
	jdbcTemplate.update(SQL, params);
	
	
}
	
}
