package com.imorochi.delivery.pedido.domain;

import java.util.List;
import java.util.Optional;

public interface PedidoDomainRepository {

    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Optional<Pedido> save(Pedido pedido);
    void delete(Long pedidoId);

    void savePedidoProducto(Long idPedido, Long idProducto);

    //ver todos los pedidos asignados a un producto
    //buscar pedidos por cualquiera de sus campos
}
