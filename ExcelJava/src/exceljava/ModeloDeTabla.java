/*Este código fue realizado por Ester Guamuch y Cyndi Medina*/
package exceljava;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

public class ModeloDeTabla extends DefaultTableModel implements TableCellRenderer {

    public ModeloDeTabla() {
        //Estabecemos el número de columnas y filas
        super.setColumnCount(25);
        super.setRowCount(450);
        nuevo();
    }
    public void nuevo() {
        //En las celdas coloca en las celdas el método Control de Celdas
        int i, j;
        for (i = 1; i < super.getColumnCount(); i++) {
            for (j = 0; j < super.getRowCount(); j++) {
                super.setValueAt(new ControldeCeldas(), j, i);
            }
        }
        //Crea la columna con los nombres del abecedario
        super.setColumnIdentifiers(new String[]{
            " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
            "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
        });
        //Crea los números de las filas de acuerdo a su número de filas
        for (int x = 0; x < super.getRowCount(); x++) {
            super.setValueAt(x + 1, x, 0);
        }

    }
    //Establece la primera columna como false, no editable
    @Override
    public boolean isCellEditable(int iFila, int iColumna) {
        if (0 == iColumna) {
            return false;
        }
        return super.isCellEditable(iFila, iColumna);
    }
       //Inicializamos las variables globales del método DefaultTableModel para establecer el vector
    public void Establecer_Vector(Vector vVector) {
        dataVector = null;
        dataVector = vVector;
    }
    //Este método establece el color de la celda, recibe un color y en el número de fila y columna determinada
    public void setBackColorAt(Color cColor, int iFila, int iColumna) {
        ControldeCeldas celdas = (ControldeCeldas) super.getValueAt(iFila, iColumna);
        celdas.cBlanco = cColor;
    }
    //Cambia el color de fuente en las celdas seleccionadas, en fila y columna establecidas
    public void setFontColorAt(Color cColor, int iFila, int iColumna) {
        ControldeCeldas celdas = (ControldeCeldas) super.getValueAt(iFila, iColumna);
        celdas.cGris = cColor;
    }
    //Establece el valor de las celdas
    public void setValueAt(Object aValue, int iRowIndex, int iColumnIndex) {
        if (aValue instanceof ControldeCeldas) {
            super.setValueAt(aValue, iRowIndex, iColumnIndex);
        } else {
            ControldeCeldas celdas = (ControldeCeldas) super.getValueAt(iRowIndex, iColumnIndex);
            celdas.sCadena = aValue.toString();
            super.setValueAt(celdas, iRowIndex, iColumnIndex);
        }
    }
    //Este método nos permite establecer el color de fondo, letra y el texto contenido en las celdas
    @Override
    public Component getTableCellRendererComponent(JTable tblTabla, Object objValor, boolean bIsSelected, boolean bHasFocus, int iRow, int iColumn) {
        DefaultTableCellRenderer dtcTabla = new DefaultTableCellRenderer();
        ControldeCeldas celdas = (ControldeCeldas) super.getValueAt(iRow, iColumn);
        if (objValor instanceof ControldeCeldas && !bIsSelected) {//Este el color de fondo, de letra y la cadena de texto que poseerán
           // las celdas cuando pasen superficialmente por ellas 
            dtcTabla.setBackground(celdas.cBlanco);
            dtcTabla.setForeground(celdas.cGris);
            dtcTabla.setText(celdas.sCadena);
            return (Component) dtcTabla;
        } else if (bHasFocus) {//Aquí los parámetros cambian cuando se enfoca una celda especifica
            Color cEnfocado = Color.ORANGE;
            dtcTabla.setBackground(cEnfocado);
            dtcTabla.setForeground(Color.GRAY);
            dtcTabla.setText(celdas.sCadena);
            return (Component) dtcTabla;

        } else {
            return dtcTabla.getTableCellRendererComponent(tblTabla, objValor, bIsSelected,
                    bHasFocus, iRow, iColumn);
        }
    }


}
