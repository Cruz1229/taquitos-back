package com.api.taquitosback.factories;

import com.api.taquitosback.models.Producto;

public interface ProductoAbstractFactory {
    Producto crearProducto(String nombre, int precio, int tipoProducto);
}
