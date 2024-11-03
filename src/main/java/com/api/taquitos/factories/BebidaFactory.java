package com.api.taquitos.factories;

import com.api.taquitos.controllers.TipoProductoController;
import com.api.taquitos.models.Bebida;
import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;
import com.api.taquitos.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;

public class BebidaFactory implements ProductoAbstractFactory {

    @Override
    public Producto crearProducto(String nombre, int precio, int tipoProducto) {
        return new Bebida(0L, nombre, precio, tipoProducto);
    }
}
