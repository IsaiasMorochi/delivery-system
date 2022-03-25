package com.imorochi.delivery.pedido.domain;

import com.imorochi.delivery.producto.domain.Producto;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "pedido_producto")
@IdClass(PedidoProductoPK.class)
@Schema(name = "Información del Pedido-Producto")
public class PedidoProducto {

    @Id
    private Pedido pedido;

    @Id
    private Producto producto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
}
