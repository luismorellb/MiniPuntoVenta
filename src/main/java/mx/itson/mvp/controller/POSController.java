/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.mvp.controller;

import javax.swing.JOptionPane;
import mx.itson.mvp.model.Producto;
import mx.itson.mvp.model.Venta;
import mx.itson.mvp.view.POSView;

/**
 *
 * @author luismorellb
 */
public class POSController {
    
    private POSView view;
    private Venta venta;

    public POSController(POSView view, Venta venta) {
        this.view = view;
        this.venta = venta;

        view.onAgregar(e -> agregarProducto());
        view.onEliminar(e -> eliminarProducto());
        view.onFinalizar(e -> finalizarVenta());
    }

    private void agregarProducto() {

        String nombre = view.getNombre();
        String precioTexto = view.getPrecio();
        String cantidadTexto = view.getCantidad();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                view,
                "El nombre del producto es obligatorio.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (precioTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                view,
                "El precio es obligatorio.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        double precio;

        try {
            precio = Double.parseDouble(precioTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                view,
                "El precio debe ser un número válido.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (precio <= 0) {
            JOptionPane.showMessageDialog(
                view,
                "El precio debe ser mayor que 0.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (precio > 10000) {
            JOptionPane.showMessageDialog(
                view,
                "El precio no puede ser mayor a 10,000.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (cantidadTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                view,
                "La cantidad es obligatoria.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        int cantidad;

        try {
            cantidad = Integer.parseInt(cantidadTexto);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                view,
                "La cantidad debe ser un número entero.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(
                view,
                "La cantidad debe ser mayor que 0.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (cantidad > 100) {
            JOptionPane.showMessageDialog(
                view,
                "La cantidad máxima permitida es 100.",
                "Error de entrada",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        if (venta.cantidadProductos() >= 20) {
            JOptionPane.showMessageDialog(
                view,
                "No se pueden agregar más de 20 productos en una venta.",
                "Error del sistema",
                JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        Producto p = new Producto(nombre, precio, cantidad);
        venta.agregarProducto(p);

        view.agregarFila(new Object[]{
            p.getNombre(),
            p.getPrecio(),
            p.getCantidad(),
            p.getSubtotal()
        });

        actualizarTotal();
        view.limpiarCampos();
    }

    private void eliminarProducto() {

        int fila = view.getFilaSeleccionada();

        if (fila < 0) {
            JOptionPane.showMessageDialog(
                view,
                "Selecciona un producto para eliminar.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        venta.eliminarProducto(fila);
        view.eliminarFila(fila);
        actualizarTotal();
    }

    private void finalizarVenta() {

        double total = venta.calcularTotal();

        JOptionPane.showMessageDialog(
            view,
            "Venta finalizada correctamente.\nTotal a pagar: $" + String.format("%.2f", total),
            "Venta completada",
            JOptionPane.INFORMATION_MESSAGE
        );

        venta.limpiarVenta();
        view.limpiarTabla();
        actualizarTotal();
    }

    private void actualizarTotal() {
        view.setTotal(venta.calcularTotal());
    }
}