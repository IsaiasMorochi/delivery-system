package com.imorochi.delivery.producto.infrastracture.repository;


import com.imorochi.delivery.producto.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;

@Component
public class ProductRepository implements DomainProductRepository {

    private final JpaProductRepository repository;

    @Autowired
    public ProductRepository(JpaProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Producto> findById(Long productoId) {
        return repository.findById(productoId);
    }

    @Override
    public List<Producto> findAll() {
        return repository.findAll();
    }

    @Override
    public Producto saveProducto(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public List<Producto> allProductsByOrder(Long pedidoId) {
        return repository.allProductsByOrder(pedidoId);
    }

    @Override
    public void deleteProducto(Producto producto) {
       repository.delete(producto);;
    }

    @Override
    public List<Producto> findAllSearchCriteria(String searchCriteria) {
        SpecificaionsBuilder<Producto> builder = new SpecificaionsBuilder<>();
        Function<SpecSearchCriteria, Specification<Producto>> converter = ProductSpecification::new;
        CriteriaParser parser=new CriteriaParser();
        return repository.findAll(builder.build(parser.parse(searchCriteria), converter));
    }

}
