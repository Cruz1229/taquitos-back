package com.api.taquitos.patterns;

public class ProductoIndividual extends ComponentePedido {
    private IProducto producto;

    public ProductoIndividual(IProducto producto) {
        this.producto = producto;
        this.nombre = producto.getDescripcion();
        this.precio = producto.getPrecio();
    }

    @Override
    public double calcularTotal() {
        return precio;
    }

    @Override
    public void agregarComponente(ComponentePedido componente) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removerComponente(ComponentePedido componente) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getCantidadProductos() {
        return 1;
    }

    public IProducto getProducto() {
        return producto;
    }
}
