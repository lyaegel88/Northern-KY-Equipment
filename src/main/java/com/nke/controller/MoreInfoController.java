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
import org.springframework.web.bind.annotation.RequestParam;

import com.nke.domain.Product;
import com.nke.domain.repository.ProductRepository;
import com.nke.exception.NoProductFoundException;

@Controller
public class MoreInfoController {
	
	@Autowired
	private ProductRepository productRepository;
	
@RequestMapping("/info")
String getProductById(@RequestParam("id") int productId, Model model) {
	
	List<Product> exists = productRepository.getProductExists(productId);
	
	if(exists== null || exists.isEmpty()) {
		throw new NoProductFoundException();
	}
	
	for (Product product : exists) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("MMM dd, yyyy");
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
	 
	model.addAttribute("product", product);
	
	return "moreInfo";
 }

}
