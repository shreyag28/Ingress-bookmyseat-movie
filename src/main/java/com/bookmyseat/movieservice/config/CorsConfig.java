package com.bookmyseat.movieservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // Allow frontend origins - using patterns for wildcard support
        config.setAllowedOriginPatterns(Arrays.asList(
            "http://localhost:*",
            "http://127.0.0.1:*",
            "http://[::1]:*",
            "http://*:4200",
            // AWS Amplify default domain pattern
            "https://main.d20avnkha3gw9q.amplifyapp.com",  // Your specific Amplify domain
            "https://bookmyseat.dockeroncloud.com"
        ));

        // Allow all HTTP methods
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));

        // Allow all headers
        config.setAllowedHeaders(List.of("*"));

        // Needed for cookie / auth header based calls from the UI
        config.setAllowCredentials(true);

        // Max age for preflight cache
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}