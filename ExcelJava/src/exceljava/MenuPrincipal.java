/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceljava;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author López
 */
public class MenuPrincipal {

    public static void main(String[] args) {
		Ventana v = new Ventana();
		v.pack();
		v.setVisible(true);
                v.setExtendedState(JFrame.MAXIMIZED_BOTH);
		v.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
