package com.tutorial.springboot.angular2.starter;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import com.google.common.collect.Lists;
import com.tutorial.springboot.angular2.starter.filters.JwtFilter;

@SpringBootApplication
@EnableConfigurationProperties
public class Angular2Application {

	public static void main(String[] args) {
		SpringApplication.run(Angular2Application.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		// 添加需要拦截的url
		List<String> urlPatterns = Lists.newArrayList();
		urlPatterns.add("/article/insert");
		registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
		return registrationBean;
	}
}
