package com.api.taquitosback.patterns;

import java.util.ArrayList;
import java.util.List;

public class DetallePedidoComponente extends ComponentePedido {
    private List<ComponentePedido> componentes = new ArrayList<>();

    @Override
    public double calcularTotal() {
        return componentes.stream()
                .mapToDouble(ComponentePedido::calcularTotal)
                .sum();
    }

    @Override
    public void agregarComponente(ComponentePedido componente) {
        componentes.add(componente);
    }

    @Override
    public void removerComponente(ComponentePedido componente) {
        componentes.remove(componente);
    }

    @Override
    public int getCantidadProductos() {
        return componentes.stream()
                .mapToInt(ComponentePedido::getCantidadProductos)
                .sum();
    }

    public List<ComponentePedido> getComponentes() {
        return componentes;
    }
}
