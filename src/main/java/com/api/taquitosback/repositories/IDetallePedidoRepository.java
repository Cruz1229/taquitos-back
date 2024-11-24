package com.api.taquitosback.repositories;

import com.api.taquitosback.models.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDetallePedidoRepository  extends JpaRepository<DetallePedido, Long> {
    List<DetallePedido> findByPedido(Long pedido);
}
