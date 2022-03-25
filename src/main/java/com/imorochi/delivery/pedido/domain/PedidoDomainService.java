package com.imorochi.delivery.pedido.domain;

import java.util.List;
import java.util.Optional;

public interface PedidoDomainService {
    List<Pedido> findAll();
    Optional<Pedido> findById(Long id);
    Optional<Pedido> save(PedidoCreator pedido);
    void delete(Long pedidoId);

}
