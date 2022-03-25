package com.imorochi.delivery.producto.domain;

public class InvalidProduct extends RuntimeException {
    public InvalidProduct() {
        super("Producto con datos incorrectos, debe registrar un producto valido.");
    }
}
