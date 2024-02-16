package com.wini.restapitemplate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/loginprocess")
                .defaultSuccessUrl("/")
                .failureUrl("/login?auth=fail")
                .and()
            .logout()
                .logoutSuccessUrl("/login")
                .permitAll()
                .and()
            .csrf().disable();


        return http.build();
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.jdbcAuthentication()
                .passwordEncoder(new MessageDigestPasswordEncoder("SHA-256"))
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT id AS username, password, 'true' FROM users WHERE id = ?")
                .authoritiesByUsernameQuery("SELECT id AS username, 'ROLE_UESR' FROM users WHERE id = ?");
    }
}
