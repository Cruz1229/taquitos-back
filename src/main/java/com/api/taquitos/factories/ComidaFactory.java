package com.api.taquitos.factories;

import com.api.taquitos.models.Bebida;
import com.api.taquitos.models.Comida;
import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;
import com.api.taquitos.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ComidaFactory implements ProductoAbstractFactory{

    @Override
    public Producto crearProducto(String nombre, int precio, int tipoProducto) {

        return new Comida(0L, nombre, precio, tipoProducto);
    }
}
