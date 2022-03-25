package com.imorochi.delivery.producto.infrastracture.repository;

import com.imorochi.delivery.producto.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaProductRepository extends JpaRepository<Producto, Long> , JpaSpecificationExecutor<Producto> {

    @Query("SELECT new com.imorochi.delivery.producto.domain.Producto(pp.producto.idProducto, pp.producto.nombre) " +
           "  FROM PedidoProducto pp, Producto p "+
           " WHERE pp.producto.idProducto = p.idProducto " +
            "  AND pp.pedido.idPedido = :idPedido "+
            "ORDER BY pp.producto.idProducto ")
    List<Producto> allProductsByOrder(@Param("idPedido") Long idPedido);

}
