package com.wini.restapitemplate.config;

import com.wini.restapitemplate.custom.CustomAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private final CustomAuthenticationEntryPoint customAutehticationEntryPoint;

    private final ApiKeyAuthFilter apiKeyAuthFilter;

    private final DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        apiKeyAuthFilter.setAuthenticationManager(new ApiKeyAuthManager(dataSource));

        http
            .antMatcher("/api/**")
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(apiKeyAuthFilter).authorizeRequests()
            .antMatchers("/h2-console/**")
                .permitAll().anyRequest().authenticated()
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(customAutehticationEntryPoint);

        return http.build();
    }
}
