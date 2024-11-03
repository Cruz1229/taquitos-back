package com.api.taquitos.patterns;

public class ExtraIngredientesDecorator extends ProductoDecorator {
    private String ingrediente;
    private double precioExtra;

    public ExtraIngredientesDecorator(IProducto producto, String ingrediente, double precioExtra) {
        super(producto);
        this.ingrediente = ingrediente;
        this.precioExtra = precioExtra;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public double getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(double precioExtra) {
        this.precioExtra = precioExtra;
    }

    @Override
    public double getPrecio() {
        return super.getPrecio() + getPrecioExtra();
    }

    @Override
    public String getDescripcion() {
        return super.getDescripcion() + " con extra de " + getIngrediente();
    }
}
