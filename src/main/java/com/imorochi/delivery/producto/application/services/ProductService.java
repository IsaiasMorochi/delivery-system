package com.imorochi.delivery.producto.application.services;

import com.imorochi.delivery.producto.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements DomainProductoService {

    private final DomainProductRepository productoRepository;

    public ProductService(DomainProductRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto saveProducto(Producto producto) throws InvalidProduct {
        if (producto == null) {
            throw new InvalidProduct();
        }
        return productoRepository.saveProducto(producto);
    }

    @Override
    public void deleteById(Long productoId) {
        Producto producto = findById(productoId).get();
        productoRepository.deleteProducto(producto);
    }

    @Override
    public List<Producto> allProductsByOrder(Long pedidoId) {
        return productoRepository.allProductsByOrder(pedidoId);
    }

    @Override
    public List<Producto> findAllSearchCriteria(String searchCriteria) {
        return Optional.ofNullable(productoRepository.findAllSearchCriteria(searchCriteria))
                .orElseThrow(() -> new ErrorSearchCriteria());
    }

    @Override
    public Optional<Producto> findById(Long productId) throws ProductNotExist {
        return Optional.ofNullable(productoRepository.findById(productId)
                .orElseThrow(() -> new ProductNotExist(productId)));
    }

}
