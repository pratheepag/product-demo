package com.example.productdemo;

import java.util.Arrays;

import javax.servlet.Servlet;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductDemoApplication.class, args);
	}
	
	@Bean(name=Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
	    return new SpringBus();
	}
	
	@Bean
	public ServletRegistrationBean<CXFServlet> cxfServletBean(){
	    ServletRegistrationBean<CXFServlet> bean = new ServletRegistrationBean<>(
	            new CXFServlet(), "/services/*");
	    bean.setLoadOnStartup(1);
	    return bean;
	}
	
	@Bean
	public Server rsServer() {
	    JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
	    endpoint.setBus(springBus());
	    endpoint.setAddress("/");
	    endpoint.setServiceBeans(Arrays.<Object>asList(new ProductResource()));
	    return endpoint.create();
	}

}
