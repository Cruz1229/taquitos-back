package com.api.taquitosback.repositories;

import com.api.taquitosback.models.TipoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoProductoRepository extends JpaRepository<TipoProducto, Long> {
}
