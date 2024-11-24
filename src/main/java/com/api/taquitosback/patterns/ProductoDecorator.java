package com.api.taquitosback.patterns;

public class ProductoDecorator implements IProducto {
    protected IProducto productoDecorado;

    public ProductoDecorator(IProducto producto) {
        this.productoDecorado = producto;
    }

    @Override
    public double getPrecio() {
        return productoDecorado.getPrecio();
    }

    @Override
    public String getDescripcion() {
        return productoDecorado.getDescripcion();
    }
}
