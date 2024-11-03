package com.api.taquitos.enums;

public enum TipoExtra {
    QUESO(10.0),
    GUACAMOLE(15.0),
    CEBOLLA(5.0),
    CILANTRO(5.0),
    SALSA_EXTRA(8.0);

    private final double precio;

    TipoExtra(double precio) {
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
}
