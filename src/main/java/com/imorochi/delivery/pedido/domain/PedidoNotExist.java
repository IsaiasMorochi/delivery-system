package com.imorochi.delivery.pedido.domain;

public class PedidoNotExist extends RuntimeException {

    public PedidoNotExist(Long id) {
        super(String.format("El pedido <%d> no existe", id));
    }
}
