package com.api.taquitos.services;

import com.api.taquitos.factories.BebidaFactory;
import com.api.taquitos.factories.ComidaFactory;
import com.api.taquitos.factories.ProductoAbstractFactory;
import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;
import com.api.taquitos.repositories.IProductoRepository;
import com.api.taquitos.repositories.ITipoProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TipoProductoService {

    @Autowired
    ITipoProductoRepository tipoProductoRepository;

    public ArrayList<TipoProducto> getTipoProductos() {
        return (ArrayList<TipoProducto>) tipoProductoRepository.findAll();
    }

    public Optional<TipoProducto> getById(Long id) {
        return tipoProductoRepository.findById(id);
    }

}
