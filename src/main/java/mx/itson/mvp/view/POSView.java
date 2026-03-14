/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.mvp.view;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luismorellb
 */
public class POSView extends JFrame {
    
    public JTextField txtNombre = new JTextField(10);
    public JTextField txtPrecio = new JTextField(10);
    public JTextField txtCantidad = new JTextField(10);

    public JButton btnAgregar = new JButton("Agregar");
    public JButton btnEliminar = new JButton("Eliminar");
    public JButton btnFinalizar = new JButton("Finalizar Venta");

    public JLabel lblTotal = new JLabel("Total: 0");

    public DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Nombre", "Precio", "Cantidad", "Subtotal"}, 0);

    public JTable tabla = new JTable(modeloTabla);

    public POSView() {

        setTitle("Mini Punto de Venta");
        setSize(650,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panelEntrada = new JPanel();

        panelEntrada.add(new JLabel("Producto"));
        panelEntrada.add(txtNombre);

        panelEntrada.add(new JLabel("Precio"));
        panelEntrada.add(txtPrecio);

        panelEntrada.add(new JLabel("Cantidad"));
        panelEntrada.add(txtCantidad);

        panelEntrada.add(btnAgregar);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnEliminar);
        panelBotones.add(btnFinalizar);

        JPanel panelTotal = new JPanel();
        panelTotal.add(lblTotal);

        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(tabla), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.WEST);
        add(panelTotal, BorderLayout.SOUTH);
    }
}