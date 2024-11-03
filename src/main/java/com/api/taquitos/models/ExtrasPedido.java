package com.api.taquitos.models;

import com.api.taquitos.enums.TipoExtra;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "extras_pedido")
public class ExtrasPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long detallePedidoId;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<TipoExtra> extras;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDetallePedidoId() {
        return detallePedidoId;
    }

    public void setDetallePedidoId(Long detallePedidoId) {
        this.detallePedidoId = detallePedidoId;
    }

    public Set<TipoExtra> getExtras() {
        return extras;
    }

    public void setExtras(Set<TipoExtra> extras) {
        this.extras = extras;
    }
}
