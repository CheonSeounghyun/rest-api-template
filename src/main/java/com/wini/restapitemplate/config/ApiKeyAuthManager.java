package com.wini.restapitemplate.config;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class ApiKeyAuthManager implements AuthenticationManager {

    private final DataSource dataSource;

    public ApiKeyAuthManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String principal = (String) authentication.getPrincipal();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = "SELECT COUNT(*) FROM api_key WHERE api_key = ? AND FORMATDATETIME(NOW(), 'yyyyMMddHHmmss') < expr_date AND expr_yn != 'Y' AND use_yn != 'N'";

        int cnt = jdbcTemplate.queryForObject(query, Integer.class, principal);
        if (cnt > 0) {
            authentication.setAuthenticated(true);
        } else {
            throw new BadCredentialsException("");
        }

        return authentication;
    }
}
