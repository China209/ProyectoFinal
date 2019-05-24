/*Este código fue hecho por Ester Guamuch y Cyndi Medina*/
package Principal;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
//Esta Clase es para modificar el diseño que viene por default en la tabla e implementamos TableCellRenderer para aplicar color a las celdas
public final class Hoja_TablaModelo extends DefaultTableModel implements TableCellRenderer {

    public Hoja_TablaModelo() {
        super(500, 500);//Establecemos 500 filas y 500 columnas, la cabecera del abecedario se genera automátiamente por
        //el método DefaultTableModel
        Nueva_Tabla();//Se llama al método nuevo
    }

    public void Nueva_Tabla() {
        int i, j;
        //Se establece en las filas y columnas el metodo del objeto para aplicar en las celdas, esto nos permitirá manipular
        //el contenido de las celdas, color e información
        for (i = 0; i <= super.getColumnCount() - 1; i++) {
            for (j = 0; j <= super.getRowCount() - 1; j++) {
                super.setValueAt(new ControldeCeldas(), i, j);
            }
        }

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
    public void setValueAt(Object aValue, int iRowIndex, int iColumnIndex) {// fila y columna 
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
