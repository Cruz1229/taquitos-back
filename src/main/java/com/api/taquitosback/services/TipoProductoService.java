package com.api.taquitosback.services;

import com.api.taquitosback.models.TipoProducto;
import com.api.taquitosback.repositories.ITipoProductoRepository;
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
