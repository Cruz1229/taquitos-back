package com.api.taquitos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class Comida extends Producto{

    public Comida(Long id, String nombre, int precio, int tipoProducto) {
        super(id, nombre, precio, tipoProducto);
    }

    public Comida() {

    }
}
