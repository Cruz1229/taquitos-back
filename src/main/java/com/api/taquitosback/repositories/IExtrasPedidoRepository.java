package com.api.taquitosback.repositories;

import com.api.taquitosback.models.ExtrasPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExtrasPedidoRepository extends JpaRepository<ExtrasPedido, Long> {
    ExtrasPedido findByDetallePedidoId(Long detallePedidoId);
}
