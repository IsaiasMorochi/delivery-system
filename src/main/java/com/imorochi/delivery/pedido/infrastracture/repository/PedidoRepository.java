package com.imorochi.delivery.pedido.infrastracture.repository;

import com.imorochi.delivery.pedido.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PedidoRepository implements PedidoDomainRepository {

    private final JpaPedidoRepository pedidoRepository;

    public PedidoRepository(JpaPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional
                .ofNullable(pedidoRepository.findById(id))
                .orElseThrow(() -> new PedidoNotExist(id));
    }

    @Override
    public Optional<Pedido> save(Pedido pedido) {
        return Optional.of(pedidoRepository.save(pedido));
    }

    @Override
    public void delete(Long pedidoId) {
        findById(pedidoId).orElseThrow(() -> new PedidoNotExist(pedidoId));
        pedidoRepository.deleteById(pedidoId);
    }

    @Override
    public void savePedidoProducto(Long idPedido, Long idProducto) {
        System.out.println(String.format("idpedido %d idproducto %d" , idPedido, idProducto));
        pedidoRepository.savePedidoProducto(idPedido, idProducto);
    }


}
