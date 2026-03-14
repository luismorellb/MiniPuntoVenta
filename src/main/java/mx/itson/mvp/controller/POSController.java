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

        try {

            String nombre = view.txtNombre.getText();
            double precio = Double.parseDouble(view.txtPrecio.getText());
            int cantidad = Integer.parseInt(view.txtCantidad.getText());

            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(null,"Nombre obligatorio");
                return;
            }

            if(precio <= 0 || precio > 10000){
                JOptionPane.showMessageDialog(null,"Precio inválido");
                return;
            }

            if(cantidad <= 0 || cantidad > 100){
                JOptionPane.showMessageDialog(null,"Cantidad inválida");
                return;
            }

            if(venta.cantidadProductos() >= 20){
                JOptionPane.showMessageDialog(null,"Máximo 20 productos");
                return;
            }

            Producto p = new Producto(nombre,precio,cantidad);
            venta.agregarProducto(p);

            view.modeloTabla.addRow(new Object[]{
                    p.getNombre(),
                    p.getPrecio(),
                    p.getCantidad(),
                    p.getSubtotal()
            });

            actualizarTotal();
            limpiarCampos();

        } catch(Exception e){
            JOptionPane.showMessageDialog(null,"Datos inválidos");
        }
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

        JOptionPane.showMessageDialog(null,"Total: $" + total);

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