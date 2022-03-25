package com.imorochi.delivery.pedido.domain;

public class InvalidProduct extends RuntimeException {
    public InvalidProduct() {
        super("El producto no puede ser vacio");
    }
}
