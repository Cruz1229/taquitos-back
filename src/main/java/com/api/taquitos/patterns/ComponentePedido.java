package com.api.taquitos.patterns;

public abstract class ComponentePedido {
    protected String nombre;
    protected double precio;

    public abstract double calcularTotal();
    public abstract void agregarComponente(ComponentePedido componente);
    public abstract void removerComponente(ComponentePedido componente);
    public abstract int getCantidadProductos();
}
