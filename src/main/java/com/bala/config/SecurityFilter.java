package com.bala.config;

import com.bala.model.Permissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityFilter {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrfConfig->csrfConfig.disable())
                .sessionManagement(sessionMangConfig->sessionMangConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(authConfig->{

                    authConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
                    authConfig.requestMatchers(HttpMethod.POST,"/auth/register").permitAll();
                    authConfig.requestMatchers(AUTH_WHITELIST).permitAll();
                    authConfig.requestMatchers("/error").permitAll();
//                    SECURED ENDPOINTS

                    authConfig.requestMatchers(HttpMethod.GET,"/books").hasAuthority(Permissions.READ_ALL_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.POST,"/books/add").hasAuthority(Permissions.SAVE_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/books/good").hasAuthority(Permissions.SAVE_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/books/{id}").hasAuthority(Permissions.READ_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/books/name/{bookname}").hasAuthority(Permissions.READ_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/books/genre/{genre}").hasAuthority(Permissions.READ_ALL_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/books/author/{author}").hasAuthority(Permissions.READ_ALL_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.DELETE,"books/delete/{id}").hasAuthority(Permissions.DELETE_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"books/update").hasAuthority(Permissions.UPDATE_ONE_BOOK.name());
                    authConfig.requestMatchers(HttpMethod.GET,"/cart").hasAuthority(Permissions.CART.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/cart/add/{id}").hasAuthority(Permissions.CART.name());
                    authConfig.requestMatchers(HttpMethod.PUT,"/cart/delete/{id}").hasAuthority(Permissions.CART.name());

                    authConfig.anyRequest().denyAll();
                });
        return http.build();
    }
    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "v3/api-docs.yaml",
            "/swagger-ui/**",
            "/swagger-ui.html",
    };
}