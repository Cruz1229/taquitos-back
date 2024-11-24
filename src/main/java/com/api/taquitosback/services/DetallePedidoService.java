package com.api.taquitosback.services;

import com.api.taquitosback.dto.DetallePedidoDTO;
import com.api.taquitosback.enums.TipoExtra;
import com.api.taquitosback.models.DetallePedido;
import com.api.taquitosback.models.ExtrasPedido;
import com.api.taquitosback.models.Producto;
import com.api.taquitosback.patterns.*;
import com.api.taquitosback.repositories.IDetallePedidoRepository;
import com.api.taquitosback.repositories.IExtrasPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetallePedidoService {

    @Autowired
    private IDetallePedidoRepository detallePedidoRepository;

    @Autowired
    private IExtrasPedidoRepository extrasPedidoRepository;

    @Autowired
    private ProductoService productoService;

    public ArrayList<DetallePedido> getDetallePedidos() {
        return (ArrayList<DetallePedido>) detallePedidoRepository.findAll();
    }

    public DetallePedido saveDetallePedido(DetallePedidoDTO dto) {
        DetallePedido detallePedido = new DetallePedido();
        detallePedido.setCantidad(dto.getCantidad());
        detallePedido.setProducto(dto.getProducto());
        detallePedido.setPedido(dto.getPedidoId());

        Optional<Producto> productoBase = productoService.getById((long)dto.getProducto());

        if (productoBase.isPresent()) {
            Producto producto = productoBase.get();
            double precioTotal = producto.getPrecio();

            // Calcular precio con extras si existen
            if (dto.getExtras() != null && !dto.getExtras().isEmpty()) {
                for (TipoExtra extra : dto.getExtras()) {
                    precioTotal += extra.getPrecio();
                }
            }

            detallePedido.setPrecioUnitarioConExtras(precioTotal);
            detallePedido.calcularSubtotal();

            DetallePedido savedDetallePedido = detallePedidoRepository.save(detallePedido);

            // Guardar extras si existen
            if (dto.getExtras() != null && !dto.getExtras().isEmpty()) {
                ExtrasPedido extrasPedido = new ExtrasPedido();
                extrasPedido.setDetallePedidoId(savedDetallePedido.getId());
                extrasPedido.setExtras(dto.getExtras());
                extrasPedidoRepository.save(extrasPedido);
            }

            return savedDetallePedido;
        }

        throw new RuntimeException("Producto no encontrado");
    }

    public boolean tieneExtra(DetallePedido dp) {
        ExtrasPedido extrasPedido = extrasPedidoRepository.findByDetallePedidoId(dp.getId());
        return extrasPedido != null && !extrasPedido.getExtras().isEmpty();
    }

    public Set<TipoExtra> getExtras(DetallePedido dp) {
        ExtrasPedido extrasPedido = extrasPedidoRepository.findByDetallePedidoId(dp.getId());
        return extrasPedido != null ? extrasPedido.getExtras() : Collections.emptySet();
    }

    public Optional<DetallePedido> getById(Long id) {
        return detallePedidoRepository.findById(id);
    }

    public List<DetallePedido> getDetallesPedidoByPedidoId(Long pedidoId) {
        return detallePedidoRepository.findByPedido(pedidoId);
    }

    public DetallePedido updateById(DetallePedido request, Long id) {
        DetallePedido detallePedido = detallePedidoRepository.findById(id).get();

        return detallePedidoRepository.save(detallePedido);
    }

    public Boolean deleteDetallePedido (Long id) {
        try {
            detallePedidoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
