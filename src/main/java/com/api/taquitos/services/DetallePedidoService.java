package com.api.taquitos.services;

import com.api.taquitos.dto.DetallePedidoDTO;
import com.api.taquitos.enums.TipoExtra;
import com.api.taquitos.models.DetallePedido;
import com.api.taquitos.models.ExtrasPedido;
import com.api.taquitos.models.Pedido;
import com.api.taquitos.models.Producto;
import com.api.taquitos.patterns.*;
import com.api.taquitos.repositories.IDetallePedidoRepository;
import com.api.taquitos.repositories.IExtrasPedidoRepository;
import com.api.taquitos.repositories.IPedidoRepository;
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

//        if (productoBase.isEmpty()) {
//            throw new RuntimeException("Producto no encontrado");
//        }

        IProducto productoFinal = productoBase.get();

        if (dto.getExtras() != null && !dto.getExtras().isEmpty()) {
            for (TipoExtra extra : dto.getExtras()) {
                productoFinal = new ExtraIngredientesDecorator(
                        productoFinal,
                        extra.name().toLowerCase().replace('_', ' '),
                        extra.getPrecio()
                );
            }
        }

        ProductoIndividual productoIndividual = new ProductoIndividual(productoFinal);

        DetallePedidoComponente detallePedidoComponente = new DetallePedidoComponente();
        detallePedidoComponente.agregarComponente(productoIndividual);

        double subtotal = detallePedidoComponente.calcularTotal() * dto.getCantidad();
        detallePedido.setSubtotal((int)subtotal);

//        DetallePedidoComponente detallePedidoComponente = new DetallePedidoComponente();
//
//        for (int i = 0; i < dto.getCantidad(); i++) {
//           detallePedidoComponente.agregarComponente(new ProductoIndividual(productoFinal));
//        }


        DetallePedido saveDetallePedido = detallePedidoRepository.save(detallePedido);

        if (dto.getExtras() != null && !dto.getExtras().isEmpty()) {
            ExtrasPedido extrasPedido = new ExtrasPedido();
            extrasPedido.setDetallePedidoId(saveDetallePedido.getId());
            extrasPedido.setExtras(dto.getExtras());
            extrasPedidoRepository.save(extrasPedido);
        }

        return saveDetallePedido;
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
