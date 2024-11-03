package com.api.taquitos.repositories;

import com.api.taquitos.models.DetallePedido;
import com.api.taquitos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetallePedidoRepository  extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido(Long pedido);
}
