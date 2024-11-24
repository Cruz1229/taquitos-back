package com.api.taquitosback.repositories;

import com.api.taquitosback.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findByTipoProducto(Long tipoComida);
}
