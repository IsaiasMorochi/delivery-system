package com.imorochi.delivery.producto.infrastracture.rest.exception;

import com.imorochi.delivery.producto.domain.ErrorSearchCriteria;
import com.imorochi.delivery.shared.infrastructure.exceptions.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestController
@ControllerAdvice
public class ProductRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorSearchCriteria.class)
    public final ResponseEntity<Object> modelsException(ErrorSearchCriteria ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
