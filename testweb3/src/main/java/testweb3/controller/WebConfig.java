package testweb3.controller;

import javax.servlet.http.HttpServlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import testweb3.servlet.*;
import testweb3.filter.*;

@Configuration
public class WebConfig {
	
   @Bean	
   public ServletRegistrationBean<HttpServlet> countryServlet() {
	   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
	   servRegBean.setServlet(new HelloCountryServlet());
	   servRegBean.addUrlMappings("/country/*");
	   servRegBean.setLoadOnStartup(1);
	   return servRegBean;
   }
   
   @Bean	
   public ServletRegistrationBean<HttpServlet> loginServlet() {
	   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
	   servRegBean.setServlet(new LoginServlet());
	   servRegBean.addUrlMappings("/login/*");
	   servRegBean.setLoadOnStartup(1);
	   return servRegBean;
   }
   
   @Bean	
   public ServletRegistrationBean<HttpServlet> chatServlet() {
	   ServletRegistrationBean<HttpServlet> servRegBean = new ServletRegistrationBean<>();
	   servRegBean.setServlet(new ChatServlet());
	   servRegBean.addUrlMappings("/home");
	   servRegBean.setLoadOnStartup(1);
	   return servRegBean;
   }
   
   @Bean
   public FilterRegistrationBean<JDBCFilter> jdbcFilter() {
	   
	   FilterRegistrationBean<JDBCFilter> registrationBean = new FilterRegistrationBean<>();
	   registrationBean.setFilter(new JDBCFilter());
	   registrationBean.addUrlPatterns("/*");
	   return registrationBean;
   }
   
   @Bean
   public FilterRegistrationBean<CookieFilter> cookieFilter() {
	   
	   FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();
	   registrationBean.setFilter(new CookieFilter());
	   registrationBean.addUrlPatterns("/*");
	   return registrationBean;
   }
   
   @Bean
   public FilterRegistrationBean<EncodingFilter> encodingFilter() {
	   
	   FilterRegistrationBean<EncodingFilter> registrationBean = new FilterRegistrationBean<>();
	   registrationBean.setFilter(new EncodingFilter());
	   registrationBean.addUrlPatterns("/*");
	   return registrationBean;
   }
   
}