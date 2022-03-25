package com.imorochi.delivery.producto.domain;

public class ProductNotExist extends RuntimeException {
    public ProductNotExist(Long id) {
        super(String.format("Producto con el ID: <%d> no existe.", id));
    }
}
