package com.imorochi.delivery.pedido.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "pedido")
@Schema(name = "Información del Pedido")
public class Pedido {

    @Id
    @Column(name = "id_pedido")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime fecha;

    @Size(min = 3, max = 200, message = "Descripción debe tener minimo 3 caracteres.")
    @Column(name = "descripcion")
    @Schema(description = "Descripción debe tener minimo 3 caracteres.")
    private String descripcion;

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return idPedido.equals(pedido.idPedido);
    }

    @Override
    public int hashCode() {
        return idPedido.hashCode();
    }
}
