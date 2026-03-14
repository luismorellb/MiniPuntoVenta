/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.mvp.model;

import java.util.ArrayList;

/**
 *
 * @author luismorellb
 */
public class Venta {
    
    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void eliminarProducto(int index) {
        productos.remove(index);
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public double calcularTotal() {

        double total = 0;

        for (Producto p : productos) {
            total += p.getSubtotal();
        }

        return total;
    }

    public int cantidadProductos() {
        return productos.size();
    }

    public void limpiarVenta() {
        productos.clear();
    }
}