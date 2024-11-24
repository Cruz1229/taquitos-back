package com.api.taquitosback.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class Bebida extends Producto{

    public Bebida(Long id, String nombre, int precio, int tipoProducto) {
        super(id, nombre, precio, tipoProducto);
    }

    public Bebida() {

    }
}
