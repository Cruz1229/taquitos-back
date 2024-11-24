package com.api.taquitosback.factories;

import com.api.taquitosback.models.Comida;
import com.api.taquitosback.models.Producto;

public class ComidaFactory implements ProductoAbstractFactory{

    @Override
    public Producto crearProducto(String nombre, int precio, int tipoProducto) {

        return new Comida(0L, nombre, precio, tipoProducto);
    }
}
