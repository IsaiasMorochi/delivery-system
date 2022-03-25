package com.imorochi.delivery.pedido.infrastracture.rest.controllers.exception;


import com.imorochi.delivery.producto.domain.ProductNotExist;
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
public class PedidoRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotExist.class)
    public final ResponseEntity<Object> modelsException(ProductNotExist ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

}
