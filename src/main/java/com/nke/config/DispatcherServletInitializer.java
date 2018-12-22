package com.nke.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.nke.config.ProdDataSource;
import com.nke.config.RootApplicationContextConfig;
import com.nke.config.WebApplicationContextConfig;
import com.nke.filter.HttpsEnforcer;

public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		
		return new Class[] {RootApplicationContextConfig.class, ProdDataSource.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		
		return new Class[] {WebApplicationContextConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		
		servletContext.setInitParameter("spring.profiles.active", System.getenv("PC_ENV"));
	}
	
	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{new HttpsEnforcer()};
	}
}