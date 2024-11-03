package com.api.taquitos.factories;

import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;

public interface ProductoAbstractFactory {
    Producto crearProducto(String nombre, int precio, int tipoProducto);
}
