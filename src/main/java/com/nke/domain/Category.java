package com.nke.domain;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class Category implements Serializable{

	private static final long serialVersionUID = 1115365200097205442L;
	
	private int categoryId;
	private String createdAt;
	private String updatedAt;
	private String title;
	private int photoId;
	private String photoUrl;
	private int photoVersion;
	private String publicId;
	
	private MultipartFile categoryImage;
	
	public Category() {
		super();
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		
		this.createdAt = createdAt;
		
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getPhotoId() {
		return photoId;
	}
	public void setPhotoId(int photoId) {
		this.photoId = photoId;
	}
	
	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public int getPhotoVersion() {
		return photoVersion;
	}

	public void setPhotoVersion(int photoVersion) {
		this.photoVersion = photoVersion;
	}
	
	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public MultipartFile getCategoryImage() {
		return categoryImage;
	}

	public void setCategoryImage(MultipartFile categoryImage) {
		this.categoryImage = categoryImage;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		return true;
	}
	

}
