package com.nke.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		
	  auth.jdbcAuthentication().dataSource(dataSource)
	    .passwordEncoder(passwordEncoder())
		.usersByUsernameQuery(
			"select username, password, enabled from user where username=?")
		.authoritiesByUsernameQuery(
			"select username, role from user where username=?");
	}	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
		.antMatchers("/", "/login*").permitAll()
		.antMatchers("/admin/**").hasRole("ADMIN").and().formLogin()
			.usernameParameter("userId")
			.passwordParameter("password")
			.loginPage("/login")
			.defaultSuccessUrl("/admin")
			.failureUrl("/login?error")
			.and().exceptionHandling().accessDeniedPage("/login?accessDenied")
			.and().logout().logoutSuccessUrl("/login?logout")
			.and().csrf().disable();
			}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		
		return encoder;
	}
	
}