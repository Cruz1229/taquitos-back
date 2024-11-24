package com.api.taquitosback.controllers;

import com.api.taquitosback.models.Pedido;
import com.api.taquitosback.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ArrayList<Pedido> getPedidos() {
        return this.pedidoService.getPedidos();
    }

    @PostMapping
    public Pedido savePedido(@RequestBody Pedido p) {
        return this.pedidoService.savePedido(p);
    }

    @GetMapping(path = "{id}")
    public Optional<Pedido> getPedidoById(@PathVariable Long id) {
        return this.pedidoService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedidoById(@RequestBody Pedido request, @PathVariable("id") Long id) {
        try {
            Pedido updatePedido = this.pedidoService.updateById(request, id);
            return ResponseEntity.ok(updatePedido);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.pedidoService.deletePedido(id);

        if (ok)
            return "Pedido eliminado";
        else
            return "Error al eliminar el pedidop";

    }
}
