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

        view.btnAgregar.addActionListener(e -> agregarProducto());
        view.btnEliminar.addActionListener(e -> eliminarProducto());
        view.btnFinalizar.addActionListener(e -> finalizarVenta());
    }

    private void agregarProducto() {

        String nombre = view.txtNombre.getText().trim();
        String precioTexto = view.txtPrecio.getText().trim();
        String cantidadTexto = view.txtCantidad.getText().trim();

        // Validar nombre
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(
                    view,
                    "El nombre del producto es obligatorio.",
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

        view.modeloTabla.addRow(new Object[]{
                p.getNombre(),
                p.getPrecio(),
                p.getCantidad(),
                p.getSubtotal()
        });

        actualizarTotal();
        limpiarCampos();
    }

    private void eliminarProducto(){

        int fila = view.tabla.getSelectedRow();

        if(fila >= 0){

            venta.eliminarProducto(fila);
            view.modeloTabla.removeRow(fila);

            actualizarTotal();
        }
    }

    private void finalizarVenta(){

        double total = venta.calcularTotal();

        JOptionPane.showMessageDialog(
        view,
        "Venta finalizada correctamente.\nTotal a pagar: $" + total,
        "Venta completada",
        JOptionPane.INFORMATION_MESSAGE
        );

        venta.limpiarVenta();
        view.modeloTabla.setRowCount(0);

        actualizarTotal();
    }

    private void actualizarTotal(){

        view.lblTotal.setText("Total: " + venta.calcularTotal());
    }

    private void limpiarCampos(){

        view.txtNombre.setText("");
        view.txtPrecio.setText("");
        view.txtCantidad.setText("");
    } 
}