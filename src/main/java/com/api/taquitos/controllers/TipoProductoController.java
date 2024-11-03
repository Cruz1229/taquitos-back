package com.api.taquitos.controllers;

import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;
import com.api.taquitos.services.ProductoService;
import com.api.taquitos.services.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/tipo_productos")
@CrossOrigin(origins = "*")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping
    public ArrayList<TipoProducto> getTipoProductos() {
        return this.tipoProductoService.getTipoProductos();
    }

    @GetMapping("/{id}")
    public Optional<TipoProducto> getTipoProductoById(@PathVariable Long id) {
        return this.tipoProductoService.getById(id);
    }
}
