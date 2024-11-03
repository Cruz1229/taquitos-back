package com.api.taquitos.repositories;

import com.api.taquitos.models.Pedido;
import com.api.taquitos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}
