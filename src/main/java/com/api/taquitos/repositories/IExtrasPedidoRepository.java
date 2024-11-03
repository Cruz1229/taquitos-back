package com.api.taquitos.repositories;

import com.api.taquitos.models.ExtrasPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExtrasPedidoRepository extends JpaRepository<ExtrasPedido, Long> {
    ExtrasPedido findByDetallePedidoId(Long detallePedidoId);
}
