package com.ibm.flight.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		// permit all domain invoke
		config.addAllowedOriginPattern("*");
		// permit all header
		config.addAllowedHeader("*");
		// permit all httpmethod
		config.addAllowedMethod("*");
		// permit cookies
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
