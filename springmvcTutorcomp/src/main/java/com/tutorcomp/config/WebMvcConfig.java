package com.tutorcomp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tutorcomp" })
public class WebMvcConfig implements WebMvcConfigurer {

	@Bean
	public InternalResourceViewResolver resolver() {
		System.out.println("WebMvcConfig :: resolver :: start");

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		try {
			resolver.setViewClass(JstlView.class);
			resolver.setPrefix("/WEB-INF/views/");
			resolver.setSuffix(".jsp");
		} catch (Exception e) {
			System.out.println("WebMvcConfig :: resolver :: ERROR :: " + e);
		}
		System.out.println("WebMvcConfig :: resolver :: end");

		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		System.out.println("WebMvcConfig :: addResourceHandlers :: start");
		try {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		} catch (Exception e) {
			System.out.println("WebMvcConfig :: addResourceHandlers :: ERROR :: " + e);
		}
		System.out.println("WebMvcConfig :: addResourceHandlers :: end");
	}
}
