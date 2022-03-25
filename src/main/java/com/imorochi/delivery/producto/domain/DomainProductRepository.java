package com.imorochi.delivery.producto.domain;

import java.util.List;
import java.util.Optional;

public interface DomainProductRepository {
    List<Producto> findAll();
    Optional<Producto> findById(Long productoId);
    void deleteProducto(Producto producto);
    Producto saveProducto(Producto producto);

    // Listar todos los produtos por pedido
    List<Producto> allProductsByOrder(Long pedidoId);

    // Permite buscar por cualesquiera de sus campos de producto
    List<Producto> findAllSearchCriteria(String searchCriteria);
}
