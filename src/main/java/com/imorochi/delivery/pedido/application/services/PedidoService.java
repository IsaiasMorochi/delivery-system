package com.imorochi.delivery.pedido.application.services;

import com.imorochi.delivery.pedido.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService implements PedidoDomainService {

    private final PedidoDomainRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoDomainRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Transactional
    @Override
    public Optional<Pedido> save(@Valid @RequestBody PedidoCreator pedidoCreator) {
        pedidoRepository.save(pedidoCreator.getPedido());
        pedidoCreator.getListProducts()
                .forEach(producto -> pedidoRepository
                        .savePedidoProducto(pedidoCreator
                                        .getPedido()
                                        .getIdPedido(),
                                producto.getIdProducto()));
        return Optional.of(pedidoCreator.getPedido());
    }

    @Override
    public void delete(Long pedidoId) {
        findById(pedidoId);
        pedidoRepository.delete(pedidoId);
    }

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return Optional.ofNullable(pedidoRepository.findById(id).orElseThrow(() -> new PedidoNotExist(id)));
    }

}
