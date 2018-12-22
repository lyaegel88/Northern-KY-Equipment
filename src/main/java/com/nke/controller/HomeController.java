package com.nke.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nke.domain.Product;

import com.nke.domain.repository.ProductRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ProductRepository productRepository;

@RequestMapping("/")
public String welcome(Model model) {
	
	List<Product> randomProducts = productRepository.getRandomProducts();
		
	if (randomProducts.isEmpty() || randomProducts.size() < 3) {
			 Product product1 = new Product();
			 product1.setPhotoUrl("img/john_deere_419.png");
			 product1.setTitle("John Deere 419");
			 
			 Product product2 = new Product();
			 product2.setPhotoUrl("img/john_deere_gator.png");
			 product2.setTitle("John Deere Gator");
			 
			 Product product3 = new Product();
			 product3.setPhotoUrl("img/kubota_la1251.png");
			 product3.setTitle("Kubota LA1251");
			 
				model.addAttribute("random1", product1);
				model.addAttribute("random2", product2);
				model.addAttribute("random3", product3);
		}else {
			
				
				for (Product product : randomProducts) {
					
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
				
				 Product product1 = new Product();
				 product1 = randomProducts.get(0);
				 
				 Product product2 = new Product();
				 product2 = randomProducts.get(1);
				 
				 Product product3 = new Product();
				 product3 = randomProducts.get(2);
				 
					model.addAttribute("random1", product1);
					model.addAttribute("random2", product2);
					model.addAttribute("random3", product3);
		}
	
	List<Product> moreInfoProducts = productRepository.getTwoProducts();
		
	if (moreInfoProducts.isEmpty() || moreInfoProducts.size() < 2) {

		 	Product product4 = new Product();
		 	product4.setProductId(10);
		 	product4.setPhotoUrl("img/sportsman_4_wheeler.png");
		 	product4.setTitle("Sportsman Quad");
		 	product4.setCreatedAt("Nov 3, 2018");
		 	
		 	Product product5 = new Product();
		 	product5.setProductId(12);
		 	product5.setPhotoUrl("img/john_deere_gator.png");
		 	product5.setTitle("John Deere Gator");
		 	product5.setCreatedAt("Nov 3, 2018");
		 	
			
			model.addAttribute("moreInfo1", product4);
			model.addAttribute("moreInfo2", product5);
			
		}else {
			
					for (Product product : moreInfoProducts) {
						
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
					
					 Product product4 = new Product();
					 product4 = moreInfoProducts.get(0);
					 
					 Product product5 = new Product();
					 product5 = moreInfoProducts.get(1);
					
						model.addAttribute("moreInfo1", product4);
						model.addAttribute("moreInfo2", product5);
		}

		return "index";
	}
}