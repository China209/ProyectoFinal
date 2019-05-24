/*Este código fue hecho por Ester Guamuch y Cyndi Medina*/
package Principal;

import java.awt.Color;
import java.io.Serializable;

public class ControldeCeldas extends Object implements Serializable, Cloneable {
        //Variables globales que establecemos como predeterminadas para las celdas
        public Color cBlanco = Color.white;
        public Color cGris = Color.gray;
        public String sCadena = "";

        @Override
        //Se retorna el valor en cadena del contenido de las celdas
        public String toString() {
            return sCadena;
        }

        @Override//Nos permite "clonar" lo que en las celdas existe con sus propiedades
        public  Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }