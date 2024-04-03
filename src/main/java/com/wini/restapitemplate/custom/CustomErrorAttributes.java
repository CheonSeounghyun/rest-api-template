package com.wini.restapitemplate.custom;

import com.wini.restapitemplate.api.ApiResponseConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    Logger logger = LoggerFactory.getLogger(CustomErrorAttributes.class);

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
//        Exception ex = (Exception) webRequest.getAttribute("org.springframework.web.servlet.DispatcherServlet.EXCEPTION",0);
        Exception ex = (Exception) webRequest.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR",0);
        Map<String, Object> result = super.getErrorAttributes(webRequest, options.excluding(ErrorAttributeOptions.Include.STACK_TRACE));
        Map<String, Object> resultBody = new LinkedHashMap<>();

        resultBody.put("status", ApiResponseConstants.STATUS_ERROR);
        resultBody.put("message", result.get("error"));

        logger.error("message", ex);
        resultBody.put("detail", ex.getMessage());

        return resultBody;
    }
}
