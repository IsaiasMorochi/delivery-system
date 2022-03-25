package com.imorochi.delivery.pedido.domain;

import com.imorochi.delivery.producto.domain.Producto;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class PedidoProductoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false) //, foreignKey = @ForeignKey(name = "fk_pedido_producto"))
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false) //, foreignKey = @ForeignKey(name = "fk_producto_pedido"))
    private Producto producto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoProductoPK)) return false;
        PedidoProductoPK that = (PedidoProductoPK) o;
        if (!producto.equals(that.producto)) return false;
        return pedido.equals(that.pedido);
    }

    @Override
    public int hashCode() {
        int result = producto.hashCode();
        result = 31 * result + producto.hashCode();
        return result;
    }
}
