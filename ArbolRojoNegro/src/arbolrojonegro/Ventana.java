package arbolrojonegro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ventana extends JFrame {

    JPanel panel;
    JPanel panel2;
    ArbolRojoNegro arbolrojinegro = new ArbolRojoNegro();//creamos instancia arbol rojinegro
    JButton bInicializar = new JButton("Inicializar Arbol");
    JButton bInsertar = new JButton("Insertar");
    JButton bBorrar = new JButton("Borrar");
    JButton bDibujar = new JButton("Dibujar");
    JButton bLimpiarl = new JButton("Limpiar lienzo");
    JTextField numerosI = new JTextField();
    JTextField numerosE = new JTextField();
    int Ancho = 800;
    int Alto = 600;

    public Ventana() {
        this.setResizable(true);
        setTitle("Arbol Roji - negro");
        setLayout(null);
        setBounds(100, 50, Ancho, Alto);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        componentes();
    }

    private void componentes() {
        colocarPanel();
        colocarPanel2();
        botones();
        cajasDeTexto();
    }

    private void colocarPanel() {
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(5, 5, 780, 250);
        //panel.setBackground(Color.cyan);
        this.getContentPane().add(panel);

    }

    private void colocarPanel2() {
        panel2 = new JPanel();
        panel2.setBounds(5, 250, 1600, 400);
        this.getContentPane().add(panel2);
        //panel2.setBackground(Color.blue);
        panel2.setLayout(null);
        //panel2.setLocation(0, 300);
    }

    private void botones() {
        panel.add(bInicializar);
        bInicializar.setBounds(10, 120, 385, 20);
        panel.add(bInsertar);
        bInsertar.setBounds(10, 35, 190, 20);
        bInsertar.setVisible(false);
        panel.add(bBorrar);
        bBorrar.setBounds(205, 35, 190, 20);
        bBorrar.setVisible(false);
        panel.add(bDibujar);
        bDibujar.setBounds(10, 80, 190, 20);
        bDibujar.setVisible(false);
        panel.add(bLimpiarl);
        bLimpiarl.setBounds(205, 80, 190, 20);
        bLimpiarl.setVisible(false);

        ActionListener oyente = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // paint p = new paint(g);

                Dibujar d = new Dibujar();

                if (e.getSource() == bInicializar) {
                    panel2.removeAll();
                    JOptionPane.showMessageDialog(getContentPane(), "Arbol inicializado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    bInsertar.setVisible(true);
                    bBorrar.setVisible(true);
                    bDibujar.setVisible(true);
                    bLimpiarl.setVisible(true);
                    numerosI.setVisible(true);
                    numerosE.setVisible(true);
                    bInicializar.setVisible(false);
                }
                if (e.getSource() == bInsertar) {
                    try {
                        JOptionPane.showMessageDialog(getContentPane(), "Valor ingresado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                        arbolrojinegro.insert(Integer.parseInt(numerosI.getText()));
                        numerosI.setText("");
                        //}
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(getContentPane(), "Inserte un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
                        System.out.println("Inserte un valor valido");
                    }

                }

                if (e.getSource() == bBorrar) {
                    try {
                        JOptionPane.showMessageDialog(getContentPane(), "Numero eliminado", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                        arbolrojinegro.eliminarNodo(Integer.parseInt(numerosE.getText()));
                        numerosI.setText("");
                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(getContentPane(), "Inserte un valor valido", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                }

                if (e.getSource() == bDibujar) {
                    
                    ArrayList array = arbolrojinegro.inOrden(arbolrojinegro.getRaiz());
                    int indice = array.indexOf(Integer.toString(arbolrojinegro.getRaiz().clave));
                    System.out.println("lista " + array);
                    System.out.println("indice " + indice);
                    d.DibujaArbol(panel2.getGraphics(), (Ancho/2) , 30, arbolrojinegro.getRaiz(), panel2);
                }
                if (e.getSource() == bLimpiarl) {
                    JOptionPane.showMessageDialog(getContentPane(), "Limpiando lienzo, recuerde siempre limpiarlo antes de darle a dibujar", "Atencion", JOptionPane.INFORMATION_MESSAGE);
                    panel2.repaint();
                }

            }
        };
        bInicializar.addActionListener(oyente);
        bInsertar.addActionListener(oyente);
        bBorrar.addActionListener(oyente);
        bDibujar.addActionListener(oyente);
        bLimpiarl.addActionListener(oyente);
    }

    private void cajasDeTexto() {
        panel.add(numerosI);
        numerosI.setBounds(10, 10, 190, 20);
        numerosI.setVisible(false);
        panel.add(numerosE);
        numerosE.setBounds(205, 10, 190, 20);
        numerosE.setVisible(false);
    }

}
