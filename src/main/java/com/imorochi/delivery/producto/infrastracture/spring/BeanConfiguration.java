package com.imorochi.delivery.producto.infrastracture.spring;

import com.imorochi.delivery.DeliveryApplication;
import com.imorochi.delivery.producto.domain.DomainProductRepository;

import com.imorochi.delivery.producto.application.services.ProductService;
import com.imorochi.delivery.producto.domain.DomainProductoService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = DeliveryApplication.class)
public class BeanConfiguration {
    @Bean
    DomainProductoService productoService(final DomainProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
