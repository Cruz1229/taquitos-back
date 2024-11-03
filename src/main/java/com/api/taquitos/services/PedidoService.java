package com.api.taquitos.services;

import com.api.taquitos.factories.BebidaFactory;
import com.api.taquitos.factories.ComidaFactory;
import com.api.taquitos.factories.ProductoAbstractFactory;
import com.api.taquitos.models.Pedido;
import com.api.taquitos.models.Producto;
import com.api.taquitos.repositories.IPedidoRepository;
import com.api.taquitos.repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private IPedidoRepository pedidoRepository;

    public ArrayList<Pedido> getPedidos() {
        return (ArrayList<Pedido>) pedidoRepository.findAll();
    }

    public Pedido savePedido(Pedido p) {;

        Pedido pedido = new Pedido(0L, p.getTotal());

        return pedidoRepository.save(pedido);
    }

    public Optional<Pedido> getById(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido updateById(Pedido request, Long id) {
        Pedido pedido = pedidoRepository.findById(id).get();

        return pedidoRepository.save(pedido);
    }

    public Boolean deletePedido (Long id) {
        try {
            pedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
