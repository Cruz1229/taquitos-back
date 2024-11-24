package com.api.taquitosback.controllers;

import com.api.taquitosback.models.TipoProducto;
import com.api.taquitosback.services.TipoProductoService;
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
