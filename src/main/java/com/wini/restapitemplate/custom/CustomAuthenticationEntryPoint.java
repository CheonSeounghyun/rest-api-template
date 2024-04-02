package com.wini.restapitemplate.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wini.restapitemplate.api.ApiErrorResponse;
import com.wini.restapitemplate.api.ApiResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        logger.error("message", authException);

        ApiErrorResponse responseBody = new ApiErrorResponse(ApiResponseConstants.STATUS_ERROR, ApiResponseConstants.MESSAGE_API_ACCESS_DENIED, null);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }
}
