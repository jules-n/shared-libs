package com.ynero.ss.exceptions.handlers.controlleradvices;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.ynero.ss.exceptions.handlers.entities.RestError;


@Configuration
public class InternalServerErrorExceptionHandlerConfig {

    @ControllerAdvice
    @ConditionalOnProperty(name = "rest.exception.handlers.include.internal-server-exception", havingValue = "true")
    private class InternalServerErrorExceptionHandler extends ResponseEntityExceptionHandler {

        @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
        @ExceptionHandler({RuntimeException.class})
        public ResponseEntity<RestError> handleInternalServerError(WebRequest webRequest) {
            var request = ((ServletWebRequest) webRequest).getRequest();
            String message = "Smth went wrong. Please, retry later";
            var restError = new RestError(HttpStatus.INTERNAL_SERVER_ERROR, message, request.getRequestURI(), request.getMethod());
            return buildResponseEntity(restError);
        }

        private ResponseEntity<RestError> buildResponseEntity(RestError error) {
            return new ResponseEntity(error, error.getStatus());
        }
    }
}
