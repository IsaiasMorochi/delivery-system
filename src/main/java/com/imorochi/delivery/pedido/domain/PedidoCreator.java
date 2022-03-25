package com.imorochi.delivery.pedido.domain;

import com.imorochi.delivery.producto.domain.Producto;

import java.util.List;

public class PedidoCreator {
    private Pedido pedido;
    private List<Producto> listProducts;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Producto> getListProducts() {
        return listProducts;
    }

    public void setListProducts(List<Producto> listProducts) {
        this.listProducts = listProducts;
    }
}
