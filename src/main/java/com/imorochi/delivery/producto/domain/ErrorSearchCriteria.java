package com.imorochi.delivery.producto.domain;

public class ErrorSearchCriteria extends RuntimeException {
    public ErrorSearchCriteria() {
        super("Criterio de busqueda con errores.");
    }
}
