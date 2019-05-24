/*
    Este código fue hecho por Karen Roldán y Jonathan González
 */
package VentanaHoja;

import Principal.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class Ventana extends JFrame {
    //Estas son nuetras variables globales que nos ayudaran en el desarrollo del entorno visual de nuestro programa
    Hoja_TablaModelo hojaCalculo = new Hoja_TablaModelo();//Llamamos al método general del modelo de la tabla
    JTable tabla = new JTable(hojaCalculo);//Creamos la tabla
    JScrollPane panelScroll = new JScrollPane(tabla);//El scrollpane para mostrar la tabla sin problemas
    ControldeCeldas[][] celdas;//Este es metodo que llamamos para controlar las celdas, pero como una matriz en la cual manipularemos filas y columnas
    MenuBar mbBarraMenu = new MenuBar();//Nuestra barra de menú

//Los menu principales
    Menu mArchivo, mEdicion, mFormato, mAyuda;

//Y los submenus que contendrán
    MenuItem miNuevo, miAbrir, miGuardar, miCerrar, miSalir, miCopiar, miPegar,
            miCortar, miSeleccion, miColorFondo, miColorLetra, miAyudaUsuario,
            miManual, miInfo;
    
//Ventanas Emergentes
    ManualUsuario manual;
    Información info;
    AyudaUsuario ayuda;
   
    public Ventana() {
        //Inicializamos los Componentes
        InicializarComponentes();
        //Maximizamos la pantalla
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //La centramos
        this.setLocationRelativeTo(null);
        //Agregamos el panel
        add(new HojaDeCalculo());
        //Establecemos la barra de menú
        this.setMenuBar(mbBarraMenu);
        //Creamos unn método que nos permita cerrar pantalla
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(Ventana.this, "DESEA SALIR DEL PROGRAMA");
                if (result == JOptionPane.YES_OPTION) {
                    Ventana.this.CierreVentana();
                } else if (result == JOptionPane.NO_OPTION) {
                }
            }
        });
    }

    public void InicializarComponentes() {
   //Inicializamos el texto que contendrá las opciones de la barra de menú
        mArchivo = new Menu("Archivo");
        mEdicion = new Menu("Edición");
        mFormato = new Menu("Formato");
        mAyuda = new Menu("Ayuda");
//Inicializamos los items que cada menu contenido en la barra de menú

//Estos serán del menú Archivo
        miNuevo = new MenuItem("Nuevo");
        miAbrir = new MenuItem("Abrir");
        miGuardar = new MenuItem("Guardar");
        miCerrar = new MenuItem("Cerrar");
        miSalir = new MenuItem("Salir");
        
//Estos serán del menú Edición
        miCopiar = new MenuItem("Copiar");
        miPegar = new MenuItem("Pegar");
        miCortar = new MenuItem("Cortar");
        miSeleccion = new MenuItem("Seleccionar Todo");
//Estos serán del menú Formato
        miColorFondo = new MenuItem("Color de Fondo");
        miColorLetra = new MenuItem("Color de Letra");
        //Estos serán del menú Ayuda
        miAyudaUsuario = new MenuItem("Ayuda a Usuario");
        miManual = new MenuItem("Manual para el Usuario");
        miInfo = new MenuItem("Información sobre el Programa");
        //Agregamos los submenus a su menu correspondiente y agregamos separadores
        //Menú Archivo
        mArchivo.add(miNuevo);
        mArchivo.addSeparator();
        mArchivo.add(miAbrir);
        mArchivo.addSeparator();
        mArchivo.add(miGuardar);
        mArchivo.addSeparator();
        mArchivo.add(miCerrar);
        mArchivo.addSeparator();
        mArchivo.add(miSalir);
        //Menú Edición
        mEdicion.add(miCopiar);
        mEdicion.addSeparator();
        mEdicion.add(miPegar);
        mEdicion.addSeparator();
        mEdicion.add(miCortar);
        mEdicion.addSeparator();
        mEdicion.add(miSeleccion);
        //Menú Formato
        mFormato.add(miColorFondo);
        mFormato.addSeparator();
        mFormato.add(miColorLetra);
        //Menú Ayuda
        mAyuda.add(miAyudaUsuario);
        mAyuda.addSeparator();
        mAyuda.add(miManual);
        mAyuda.addSeparator();
        mAyuda.add(miInfo);
        //Agregamos los menus a la barra
        mbBarraMenu.add(mArchivo);
        mbBarraMenu.add(mEdicion);
        mbBarraMenu.add(mFormato);
        mbBarraMenu.add(mAyuda);
        EventosMenu();//Llamamos a los eventos de los menus

    }
    //Creamos las acciones de los menus
    public void EventosMenu() {
        //Reestablecemos la tabla y limpiamos sus celdas y se vuelve a redibujar la tabla
        miNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Ventana.this.panelScroll.setVisible(true);
                Ventana.this.tabla.setVisible(true);
                Ventana.this.hojaCalculo.Nueva_Tabla();
                panelScroll.repaint();
                Ventana.this.repaint();
            }
        });
        /*Este evento fue hecho por Ester Guamuch y Cyndi Medina*/
        //Nos permite  abrir archivos creados por este programa y los muestra en las mismas celdas en que estaban
        miAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iResultado = JOptionPane.showConfirmDialog(Ventana.this, "DESEA ABRIR UN ARCHIVO");
                if (iResultado == JOptionPane.YES_OPTION) {
                    JFileChooser jfcArchivo = new JFileChooser();// nos permite abrir y crear un fichero
                    int iOpcion = jfcArchivo.showOpenDialog(Ventana.this);
                    if (iOpcion == JFileChooser.APPROVE_OPTION) {
                        File fArchivo = jfcArchivo.getSelectedFile();
                        jfcArchivo = null;
                        try {
                            FileInputStream fins = new FileInputStream(fArchivo);// fichero de texto para almacenar informacion 
                            ObjectInputStream ois = new ObjectInputStream(fins);
                            hojaCalculo.Establecer_Vector((Vector) ois.readObject());
                            panelScroll.setVisible(true);
                            tabla.setVisible(true);
                            Ventana.this.repaint();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }

                } else if (iResultado == JOptionPane.NO_OPTION) {
                }
            }
        });
        //Guardamos el contenido de la tabla como un objeto
        miGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iResultado = JOptionPane.showConfirmDialog(Ventana.this, "DESEA GUARDAR EL TRABAJO ACTUAL");
                if (iResultado == JOptionPane.YES_OPTION) {
                    JFileChooser jfcArhivo = new JFileChooser();
                    int iOpcion = jfcArhivo.showSaveDialog(null);
                    if (iOpcion == JFileChooser.APPROVE_OPTION) {
                        File fArchivo = jfcArhivo.getSelectedFile();
                        jfcArhivo = null;
                        try {
                            FileOutputStream fins = new FileOutputStream(fArchivo);
                            ObjectOutputStream ous = new ObjectOutputStream(fins);
                            ous.writeObject(hojaCalculo.getDataVector());
                            ous.flush();
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                } else if (iResultado == JOptionPane.NO_OPTION) {
                }
            }
        });
        
        //Cerramos la tabla
        miCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelScroll.setVisible(false);
                tabla.setVisible(false);
                Ventana.this.repaint();
            }
        });
        //Salimos del programas
        miSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int iResultado = JOptionPane.showConfirmDialog(Ventana.this, "DESEA SALIR DEL PROGRAMA");
                if (iResultado == JOptionPane.YES_OPTION) {
                    Ventana.this.CierreVentana();
                } else if (iResultado == JOptionPane.NO_OPTION) {
                }
            }
        });
        /*Este evento fue hecho por Ester Guamuch y Cyndi Medina*/
        //Copiado de las celdas como matriz
        miCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Creamos un vector por las filas y columnas
                int[] iColumnas = tabla.getSelectedColumns(), iFilas = tabla.getSelectedRows();
                celdas = new ControldeCeldas[iFilas.length][iColumnas.length];
                ControldeCeldas temporal;
                int i, j;
                for (i = 0; i <= iColumnas.length - 1; i++) {
                    for (j = 0; j <= iFilas.length - 1; j++) {
                        temporal = (ControldeCeldas) hojaCalculo.getValueAt(iFilas[j], iColumnas[i]);
                        try {
                            celdas[j][i] = (ControldeCeldas) temporal.clone();
                        } catch (CloneNotSupportedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                }
            }
        });
 
//Pegamos el contenido de las celdas copiadas o cortadas
        miPegar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int iColumnas = tabla.getSelectedColumn(), iFilas = tabla.getSelectedRow();
                if (celdas != null) {
                    int i, j;
                    for (i = 0; i <= celdas[0].length - 1; i++) {
                        if (iColumnas + i <= tabla.getColumnCount() - 1) {
                            for (j = 0; j <= celdas.length - 1; j++) {
                                if (iFilas + j <= tabla.getRowCount() - 1) {
                                    hojaCalculo.setValueAt(celdas[j][i], iFilas + j, iColumnas + i);
                                }
                            }
                        }
                    }
                }
            }
        });
 
//Cortar el contenido de las celdas
        miCortar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] iColumnas = tabla.getSelectedColumns(), iFilas = tabla.getSelectedRows();
                celdas = new ControldeCeldas[iFilas.length][iColumnas.length];
                ControldeCeldas temporal;
                int i, j;
                for (i = 0; i <= iColumnas.length - 1; i++) {
                    for (j = 0; j <= iFilas.length - 1; j++) {
                        temporal = (ControldeCeldas) hojaCalculo.getValueAt(iFilas[j], iColumnas[i]);
                        try {
                            celdas[j][i] = (ControldeCeldas) temporal.clone();
                        } catch (CloneNotSupportedException ex) {
                            System.out.println(ex.getMessage());
                        }
                        hojaCalculo.setValueAt(new ControldeCeldas(), iFilas[j], iColumnas[i]);
                    }
                }
            }
        });
        
 //Se selecciona todas las celdas
        miSeleccion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabla.selectAll();
            }
        }); 

//Cambiar color de Fondo de la celda
        miColorFondo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color cColor = JColorChooser.showDialog(Ventana.this, "Selección de Color de Fondo", Color.black);
                int[] iColumnas = tabla.getSelectedColumns(), iFilas = tabla.getSelectedRows();
                int i, j;
                for (i = 0; i <= iColumnas.length - 1; i++) {
                    for (j = 0; j <= iFilas.length - 1; j++) {
                        hojaCalculo.setBackColorAt(cColor, iFilas[j], iColumnas[i]);
                    }
                }
                tabla.clearSelection();
            }
        });
        
 //Cambiar color de Fuente de la celda
        miColorLetra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color cColor = JColorChooser.showDialog(Ventana.this, "Selección de Color de Letra", Color.black);
               // int[] a = tabla.getSelectedColumns(), b = tabla.getSelectedRows();
               int[] iColumnas = tabla.getSelectedColumns(), iFilas = tabla.getSelectedRows();
                int i, j;
                for (i = 0; i <= iColumnas.length - 1; i++) {
                    for (j = 0; j <= iFilas.length - 1; j++) {
                        hojaCalculo.setFontColorAt(cColor, iFilas[j], iColumnas[i]);
                    }
                }
                tabla.clearSelection();
            }
        });
        //Abre la ventana emergente del Manual para el Usuario
        miManual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                manual=new ManualUsuario();
                manual.setVisible(true);
            }
        });
        //Información sobre los creadores del programa
        miInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                info=new Información();
                info.setVisible(true);
            }
        });
        // Ayuda al Usuario
        miAyudaUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ayuda=new AyudaUsuario();
                ayuda.setVisible(true);
            }
        });
    }
    //Creamos una clase de tipo panel para establecer el diseño de la tabla
    public class HojaDeCalculo extends JPanel {

        public HojaDeCalculo() {
            setLayout(new BorderLayout());
            tabla.setDefaultRenderer(Object.class, hojaCalculo);
            tabla.setRowSelectionAllowed(true);
            tabla.setColumnSelectionAllowed(true);
            tabla.setSelectionForeground(Color.blue);
            tabla.setSelectionBackground(Color.cyan);
            tabla.setAutoResizeMode(0);
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setDoubleBuffered(true);
            add(panelScroll);
        }
    }
    //Cerrar Ventana
    protected void CierreVentana() {
        System.exit(0);
    }
}
