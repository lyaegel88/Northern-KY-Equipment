package com.nke.domain.repository;

import java.util.List;

import com.nke.domain.Photo;

public interface PhotoRepository {

	int addPhoto(Photo photo);

	List<Photo> getPhotoExists(String photoId);
	
	void updatePhoto(String updatedDate, String url, int version, int photoId, String publicId);

	void deletePhoto(int photoId);

}
