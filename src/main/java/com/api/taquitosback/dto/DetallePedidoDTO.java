package com.api.taquitosback.dto;

import com.api.taquitosback.enums.TipoExtra;

import java.util.Set;

public class DetallePedidoDTO {
    private int cantidad;
    private int producto;
    private Long pedidoId;
    private Set<TipoExtra> extras;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Set<TipoExtra> getExtras() {
        return extras;
    }

    public void setExtras(Set<TipoExtra> extras) {
        this.extras = extras;
    }
}
