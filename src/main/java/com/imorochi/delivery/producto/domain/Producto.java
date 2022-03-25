package com.imorochi.delivery.producto.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "producto")
@Schema(description = "Información del Producto.")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Long idProducto;

    @Size(min = 3, max = 70, message = "Nombres debe tener minimo 3 caracteres.")
    @Column(name = "nombre", nullable = false, length = 70)
    @Schema(description = "Nombre debe tener minimo 3 caracteres.")
    private String nombre;

    public Producto() {
    }

    public Producto(Long idProducto, @Size(min = 3, max = 70, message = "Nombres debe tener minimo 3 caracteres.") String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producto)) return false;
        Producto producto = (Producto) o;
        return idProducto.equals(producto.idProducto);
    }

    @Override
    public int hashCode() {
        return idProducto.hashCode();
    }
}
