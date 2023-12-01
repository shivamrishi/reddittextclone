package com.shivamr.reddittextclone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class OAuthSecurityConfigure{
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests((authorizeHttpRequests) ->
                            authorizeHttpRequests
                                    .requestMatchers("/", "", "/t2", "feed").permitAll().
                                    anyRequest().authenticated()
                    )
                    .oauth2Login(withDefaults());
            return http.build();
    }
	
	
}