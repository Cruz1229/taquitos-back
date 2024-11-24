package com.api.taquitosback.factories;

import com.api.taquitosback.models.Bebida;
import com.api.taquitosback.models.Producto;

public class BebidaFactory implements ProductoAbstractFactory {

    @Override
    public Producto crearProducto(String nombre, int precio, int tipoProducto) {
        return new Bebida(0L, nombre, precio, tipoProducto);
    }
}
