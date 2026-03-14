/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package mx.itson.mvp;

import mx.itson.mvp.controller.POSController;
import mx.itson.mvp.model.Venta;
import mx.itson.mvp.view.POSView;

/**
 *
 * @author luismorellb
 */
public class Main {

    public static void main(String[] args) {

        POSView view = new POSView();
        Venta venta = new Venta();

        new POSController(view, venta);

        view.setVisible(true);
    }
}