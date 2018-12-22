package com.nke.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
@ComponentScan("com.nke")
public class CloudinaryConfig {

	@Bean
	public Cloudinary getCloudindary() {
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "enter_cloud_name",
				  "api_key", "enter_api_key",
				  "api_secret", "enter_api_secret"));
		
		return cloudinary;
	}
	
}