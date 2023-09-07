package com.example.photoBlog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public SessionAuthenticationStrategy sessionAuthenticationStrategy(SessionRegistry sessionRegistry) {
        return new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .sessionManagement((sessionManagement) -> sessionManagement
                .maximumSessions(1) // Maximum number of allowed sessions per user
                .sessionRegistry(sessionRegistry())
                .expiredUrl("/api/user/login?expired") // URL to redirect when a session expires
                .and()
                .sessionAuthenticationStrategy(sessionAuthenticationStrategy(sessionRegistry()))
            )
            .authorizeRequests((authorizeRequests) -> authorizeRequests
                .antMatchers("/api/user/login", "/api/user/register").permitAll()
                .anyRequest().authenticated()
                
            )
            .httpBasic(Customizer.withDefaults())
            .csrf().disable();

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
