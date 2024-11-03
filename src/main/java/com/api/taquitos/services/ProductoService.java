package com.api.taquitos.services;

import com.api.taquitos.factories.BebidaFactory;
import com.api.taquitos.factories.ComidaFactory;
import com.api.taquitos.factories.ProductoAbstractFactory;
import com.api.taquitos.models.*;
import com.api.taquitos.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private IProductoRepository productoRepository;

    public ArrayList<Producto> getProductos() {
        return (ArrayList<Producto>) productoRepository.findAll();
    }

    public List<Producto> getComidas() {
        return productoRepository.findByTipoProducto(1L);
    }

    public List<Producto> getBebidas() {
        return productoRepository.findByTipoProducto(2L);
    }

    public Producto saveProducto(Producto p) {
        ProductoAbstractFactory factory;

        if (p.getTipoProducto() == 1)
            factory = new ComidaFactory();
        else
            factory = new BebidaFactory();

        Producto producto = factory.crearProducto(p.getNombre(), (int)p.getPrecio(), p.getTipoProducto());

        return productoRepository.save(producto);
    }

    public Optional<Producto> getById(Long id) {
        return productoRepository.findById(id);
    }

    public Producto updateById(Producto request, Long id) {
        Producto producto = productoRepository.findById(id).get();

        return productoRepository.save(producto);
    }

    public Boolean deletePProducto (Long id) {
        try {
            productoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
