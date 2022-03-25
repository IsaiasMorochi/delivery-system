package com.imorochi.delivery.producto.infrastracture.rest.controllers;

import com.imorochi.delivery.pedido.domain.PedidoProducto;
import com.imorochi.delivery.producto.domain.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;

import javax.validation.Valid;
import java.net.URI;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Tag(name = "products", description = "API Productos")
@RestController
@RequestMapping("/api")
public class ProductoController {

    private final DomainProductoService productoService;


    public ProductoController(DomainProductoService productoService) {
        this.productoService = productoService;
    }

    @Operation(summary = "Obtiene todos los productos", description = "Devuelve todos los productos", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Producto.class))) }
    )
    @GetMapping(value = "/v1/products", produces = { "application/json" })
    public ResponseEntity<List<Producto>> findAll() {
        List<Producto> medicos = productoService.findAll();
        return new ResponseEntity<>(medicos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene una producto por ID", description = "Devuelve un producto", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "ID proporcionado no válido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado") }
    )
    @GetMapping(value = "/v1/products/{id}", produces = { "application/json" })
    public ResponseEntity<Producto> findById(@Parameter(description = "Id del producto para devolver", required = true) @PathVariable(name = "id") Long id) {
        Producto producto = productoService.findById(id).get();
        return new ResponseEntity<>(producto, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene todos los productos asignados a un pedido", description = "Obtiene todos los productos asignados a un pedido", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = PedidoProducto.class))),
            @ApiResponse(responseCode = "400", description = "ID proporcionado no válido"),
            @ApiResponse(responseCode = "404", description = "Productos no encontrados") }
    )
    @GetMapping(value = "/v1/products/orders/{id}", produces = { "application/json" })
    public ResponseEntity<List<Producto>> findAllProductsByIdOrder(@Parameter(description = "Id del pedido para devolver todos los productos", required = true) @PathVariable(name = "id") Long id) {
       List<Producto> products = productoService.allProductsByOrder(id);;
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto por ID utilizando HATEOAS", description = "Devuelve un producto sobre el nivel #3 del 'richardson Maturity Model'", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "ID proporcionado no válido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado") }
    )
    @GetMapping(value = "/v2/products/{id}", produces = { "application/json" })
    public ResponseEntity<EntityModel<Producto>> findById(@PathVariable long id) {
        return productoService.findById(id)
                .map(product -> EntityModel.of(product,
                        linkTo(methodOn(ProductoController.class).findById(product.getIdProducto())).withSelfRel(), //
                        linkTo(methodOn(ProductoController.class).findAll()).withRel("products"))) //
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registra un producto", description = "Registra un producto", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe registro") }
    )
    @PostMapping(value = "/v1/products", produces = { "application/json" })
    public ResponseEntity<Producto> save(@Parameter(description = "Producto para registrar", required = true) @Valid @RequestBody Producto producto) {
        Producto newProducto = productoService.saveProducto(producto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newProducto.getIdProducto())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Actualiza un producto", description = "Actualiza un producto", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe registro") }
    )
    @PutMapping(value = "/v1/products/{id}", produces = { "application/json" })
    public ResponseEntity<Producto> update(@Parameter(description = "Producto para actualizar", required = true) @Valid @RequestBody Producto producto, @PathVariable(name = "id") Long productId) {
        Producto newProducto = productoService.findById(productId)
                .map(obj -> {
                    obj.setNombre(producto.getNombre());
                    return productoService.saveProducto(obj);
                }).orElseGet(() -> {
                    producto.setIdProducto(productId);
                    return productoService.saveProducto(producto);
                });

        return new ResponseEntity<>(newProducto, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto por ID", description = "Elimina un producto", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado") }
    )
    @DeleteMapping(value = "/v1/products/{id}", produces = { "application/json" })
    public ResponseEntity<Producto> deleteById(@Parameter(description = "Id del producto para eliminar", required = true) @PathVariable(name = "id") Long productId) {
        productoService.findById(productId).ifPresent(medico -> productoService.deleteById(productId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Permite realizar busqueda por cualquiera de los campos ex: search?<param><operator><string> (nombre>Sony)", description = "Obtiene todos los productos de acuerdo al criterio de parametros.", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Producto.class))),
            @ApiResponse(responseCode = "400", description = "ID proporcionado no válido"),
            @ApiResponse(responseCode = "404", description = "Productos no encontrados") }
    )
    @GetMapping(value = "/v1/products/query", produces = { "application/json" })
    public ResponseEntity<List<Producto>>search(@RequestParam(value = "search") String searchCriteria) {
        //http://localhost:9090/api/v1/products/query?search=nombre>Sony
        List<Producto> products = productoService.findAllSearchCriteria(searchCriteria);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

}
