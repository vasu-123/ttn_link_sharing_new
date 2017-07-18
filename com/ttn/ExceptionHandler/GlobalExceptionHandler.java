package com.ttn.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, Exception e) {
            System.out.println("Exception handler reached");
        return "Error occured : " +e.getLocalizedMessage()  ;
    }
}
