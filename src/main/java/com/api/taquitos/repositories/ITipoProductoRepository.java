package com.api.taquitos.repositories;

import com.api.taquitos.models.Producto;
import com.api.taquitos.models.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Long> {
}
