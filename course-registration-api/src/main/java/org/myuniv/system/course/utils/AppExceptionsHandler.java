package org.myuniv.system.course.utils;

import org.myuniv.system.course.beans.responses.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception exception, WebRequest request) {

        System.out.println(exception.getMessage());
        String description = exception.getLocalizedMessage();
        if (description == null) {
            description = exception.toString();
        }
        ErrorMessage errorMessage = new ErrorMessage(new Date(), description);

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
