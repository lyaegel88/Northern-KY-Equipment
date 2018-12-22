package com.nke.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com.nke")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {

@Override
public void configureDefaultServletHandling (DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

@Bean
public InternalResourceViewResolver getInternalResourceViewResolver() {
	InternalResourceViewResolver resolver = new InternalResourceViewResolver();
	resolver.setViewClass(JstlView.class);
	resolver.setPrefix("/WEB-INF/views/");
	resolver.setSuffix(".jsp");

		return resolver;
	}

@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   registry.addResourceHandler("/img/**")
          .addResourceLocations("/resources/images/");
	}

@Bean
public CommonsMultipartResolver multipartResolver() {
	CommonsMultipartResolver resolver = new CommonsMultipartResolver();
	resolver.setDefaultEncoding("utf-8");

	return resolver;
}

@Bean
public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(465);
	
	    mailSender.setUsername("enter_email");
	    mailSender.setPassword("enter_password");
	
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    return mailSender;
	}

/*@Bean
public PasswordEncoder passwordEncoderServer() {
	
	return new BCryptPasswordEncoder();
}*/

}