package com.imorochi.delivery.pedido.infrastracture.repository;

import com.imorochi.delivery.pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPedidoRepository extends JpaRepository<Pedido, Long> {

    @Modifying
    @Query(value = "INSERT INTO pedido_producto (id_pedido, id_producto) values (:idPedido, :idProducto)", nativeQuery = true)
    Integer savePedidoProducto(@Param("idPedido") Long idPedido, @Param("idProducto") Long idProducto);

}
