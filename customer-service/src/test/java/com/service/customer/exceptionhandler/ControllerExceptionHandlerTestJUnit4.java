package com.service.customer.exceptionhandler;

import org.junit.Before;
import org.junit.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ControllerExceptionHandlerTestJUnit4 {

    private ControllerExceptionHandler exceptionHandler;

    @Before
    public void setUp() throws Exception {
        exceptionHandler = new ControllerExceptionHandler();
    }

    @Test
    public void methodArgumentNotValidException() throws NoSuchMethodException {
        BindingResult bindingResult = new MapBindingResult(new HashMap<>(), "CustomerVO");
        bindingResult.addError(new FieldError("CustomerVO", "firstName", "Invalid name"));

        Method method = this.getClass().getMethod("setUp", (Class[]) null);
        MethodParameter parameter = new MethodParameter(method, -1);

        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(parameter, bindingResult);

        ResponseEntity responseEntity = exceptionHandler.handleMethodArgumentNotValidException(exception);

        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("Invalid name : valid input should be alphabetic and less than 20 characters", responseEntity.getBody().toString());
    }
}