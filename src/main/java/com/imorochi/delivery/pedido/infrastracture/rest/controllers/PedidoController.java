package com.imorochi.delivery.pedido.infrastracture.rest.controllers;

import com.imorochi.delivery.pedido.domain.*;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Tag(name = "orders", description = "API Pedidos")
@RestController
@RequestMapping("/api")
public class PedidoController {

    private final PedidoDomainService pedidoDomainService;

    @Autowired
    public PedidoController(PedidoDomainService pedidoDomainService) {
        this.pedidoDomainService = pedidoDomainService;
    }

    @Operation(summary = "Obtiene todos los pedidos", description = "Devuelve todos los pedidos", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Pedido.class)))}
    )
    @GetMapping(value = "/v1/orders", produces = {"application/json"})
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> pedidos = pedidoDomainService.findAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un pedido por ID", description = "Devuelve un pedido", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = Pedido.class))),
            @ApiResponse(responseCode = "400", description = "ID proporcionado no válido"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")}
    )
    @GetMapping(value = "/v1/orders/{id}", produces = {"application/json"})
    public ResponseEntity<Pedido> findById(@Parameter(description = "Id del pedido para devolver", required = true) @PathVariable(name = "id") Long id) {
        Pedido pedido = pedidoDomainService.findById(id).get();
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @Operation(summary = "Registra un pedido", description = "Registra un pedido con el listado de sus productos asociados", responses = {
            @ApiResponse(responseCode = "200", description = "Operación exitosa"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "409", description = "Ya existe registro")}
    )
    @PostMapping(value = "/v1/orders", produces = {"application/json"})
    public ResponseEntity<Pedido> save(@Valid @RequestBody PedidoCreator pedidoCreator) {
        Pedido newPedido = pedidoDomainService.save(pedidoCreator).get();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPedido.getIdPedido())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
