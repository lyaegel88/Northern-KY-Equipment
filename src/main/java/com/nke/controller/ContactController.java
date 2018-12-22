package com.nke.controller;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.HtmlUtils;

import com.nke.domain.EmailTemplate;

@Controller
public class ContactController {
	
	@Autowired
    public JavaMailSender emailSender;
	
	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contactForm(Model model) {
		EmailTemplate emailForm = new EmailTemplate();
		model.addAttribute("emailForm", emailForm);
		
		return "contact";
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public RedirectView createEmail(@ModelAttribute ("emailForm") EmailTemplate emailForm, RedirectAttributes redirectAttributes) throws IOException{
		
		emailForm.setEmailSubject("NKE Customer Inquiry from your Website");
		emailForm.setRecipientEmail("nkye.testing@gmail.com");
		
		String customerName = HtmlUtils.htmlEscape(emailForm.getCustomerName());
		String customerEmail = HtmlUtils.htmlEscape(emailForm.getCustomerEmail());
		String customerPhone = HtmlUtils.htmlEscape(emailForm.getCustomerPhone());
		String customerMessage = HtmlUtils.htmlEscape(emailForm.getCustomerInquiry());
		
		String emailMessage = "Name: " + customerName + "\n" + "Email: " + customerEmail + "\n" + "Phone Number: " 
				+ customerPhone + "\n" + "Message: " + customerMessage;
		
		 SimpleMailMessage message = new SimpleMailMessage();
         
	        message.setTo(emailForm.getRecipientEmail());
	        message.setSubject(emailForm.getEmailSubject());
	        message.setText(emailMessage);
	 
	        this.emailSender.send(message);
	        
	        System.out.println(emailMessage);
		
		redirectAttributes.addFlashAttribute("success", "<div class=\"alert alert-success\">Email was successfully sent.</div>");
		
		return new RedirectView("contact"); 
	}
}
