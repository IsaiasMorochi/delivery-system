package com.imorochi.delivery.producto.domain;

import java.util.List;
import java.util.Optional;

public interface DomainProductoService {
    List<Producto> findAll();
    Optional<Producto> findById(Long productId);
    Producto saveProducto(Producto producto) throws InvalidProduct;
    void deleteById(Long productoId);

    List<Producto> allProductsByOrder(Long pedidoId);
    List<Producto> findAllSearchCriteria(String searchCriteria);
}
