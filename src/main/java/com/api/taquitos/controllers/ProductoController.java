package com.api.taquitos.controllers;

import com.api.taquitos.models.Bebida;
import com.api.taquitos.models.Producto;
import com.api.taquitos.services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ArrayList<Producto> getProductos() {
        return this.productoService.getProductos();
    }

    @GetMapping("/comida")
    public List<Producto> getComidas() {
        return this.productoService.getComidas();
    }

    @GetMapping("/bebida")
    public List<Producto> getBebidas() {
        return this.productoService.getBebidas();
    }

    @PostMapping
    public Producto saveProducto(@RequestBody Producto p) {
        System.out.println("Nombre: " + p.getNombre());
        System.out.println("Precio: " + p.getPrecio());
        System.out.println("TipoProducto: " + p.getTipoProducto());
        return this.productoService.saveProducto(p);
    }

    @GetMapping(path = "{id}")
    public Optional<Producto> getProductoById(@PathVariable Long id) {
        return this.productoService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProductoById(@RequestBody Producto request, @PathVariable("id") Long id) {
        try {
            Producto updateProducto = this.productoService.updateById(request, id);
            return ResponseEntity.ok(updateProducto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.productoService.deletePProducto(id);

        if (ok)
            return "Producto eliminado";
        else
            return "Error al eliminar el productop";

    }
}
