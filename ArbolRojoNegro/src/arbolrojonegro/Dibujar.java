package arbolrojonegro;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Dibujar {

    ImageIcon Rojo = new ImageIcon(getClass().getResource("/Imagen/Rojo.png"));//LLamar las imagenes respectivas al color del nodo
    ImageIcon Negro = new ImageIcon(getClass().getResource("/Imagen/Negro.png"));//Nodo negro
    ImageIcon Null = new ImageIcon(getClass().getResource("/Imagen/Null.png"));//hijos de las hojas NULL
    
    ArbolRojoNegro arbolrojinegro = new ArbolRojoNegro();//instanciacion del arbol

    public static int nodoCompleto(Nodo nodo) {//definicion de si un nodo esta completo
        Nodo p = nodo;//creacion del objeto nodo
        if (p == null) {
            return 0;//0 si el nodo no existe
        } else {
            if (p.hijoIzquierdo != null && p.hijoDerecho != null) {//evalua si el nodo tiene hijo derecho e hijo izquierdo
                return nodoCompleto(p.hijoIzquierdo) + nodoCompleto(p.hijoDerecho) + 1;//retorna el valor de los hijos de forma iterativa con la misma funcion
            } else {
                return nodoCompleto(p.hijoIzquierdo) + nodoCompleto(p.hijoDerecho);
            }
        }
    }

    public void DibujaArbol(Graphics g, int x, int y, Nodo nodo, JPanel panel) {//posicionamiento de la raiz e inicio del procedimiento recursivo DibujarArbol2 para dibujar todos los nodos
        ArbolRojoNegro arbolrojinegro = new ArbolRojoNegro();
        Nodo padre = nodo;
        //panel.removeAll();
        //nodoCompleto(padre);
        DibujaArbol2(g, x, y, nodo);//manera recursiva

    }

    public void DibujaArbol2(Graphics g, int x, int y, Nodo nodo) {
        ArbolRojoNegro arbolrojinegro = new ArbolRojoNegro();
        Nodo padre = nodo;

        if (padre == null) {
            System.out.println("nodo vacio");
        } else {
            if (padre.clave == 0) {//clave == 0 significa nodo NULL, 
                DibujaArbol3(g, x - 30, y + 30);//DibujaArbol3 dibuja los nodos NULL
                return;
            }
            int extra = nodoCompleto(padre) * 15;//genera un intervalo de espacio entre nodos para su impresion
            System.out.println("extra " + extra);
            arbolrojinegro.getColor(padre);
            
            if (padre.hijoIzquierdo.clave==0){//Si es hijo izquierdo dibuja una linea en dirección al padre
                   g.setColor(Color.black);
                   g.drawLine(x - 3, y + 3, x - 19 - extra, y + 25);
            }
            
            if (padre.hijoDerecho.clave==0){//Si es hijo derecho dibuja una linea en dirección al padre
                   g.setColor(Color.black);
                   g.drawLine(x + 13, y - 3, x + 26 + extra, y + 25);
            }
            
            
            if (padre.hijoIzquierdo != null && padre.hijoIzquierdo.clave != 0) {
                g.setColor(Color.black);
                g.drawImage(Negro.getImage(), x-10, y-20,null);
                g.drawLine(x - 3, y + 3, x - 19 - extra, y + 25);
            }
            if (padre.hijoDerecho != null && padre.hijoDerecho.clave != 0) {
                g.setColor(Color.black);
                g.drawLine(x , y , x + 30 + extra, y + 30);
            }
            if (arbolrojinegro.getColor(padre) == 1) {//Determina si el nodo es negro o rojo e imprime en pantalla su respectivo nodo
                g.setColor(Color.white);
                g.drawImage(Rojo.getImage(), x-10, y-20,null);
                g.drawString(Integer.toString(padre.clave), x, y);
            } else if (arbolrojinegro.getColor(padre) == 0) {//Determina si el nodo es negro o rojo e imprime en pantalla su respectivo nodo
                g.setColor(Color.white);
                g.drawImage(Negro.getImage(), x-10, y-20,null);
                g.drawString(Integer.toString(padre.clave), x, y);
            }
            //Llama de manera recursiva el procedo DibujarArbol2 para asi dibujar todos los nodos
            DibujaArbol2(g, x - 30 - extra, y + 30, padre.hijoIzquierdo);
            DibujaArbol2(g, x + 30 + extra, y + 30, padre.hijoDerecho);
            
            
        }
    }
    
    
    public void DibujaArbol3(Graphics g, int x, int y){
        g.drawImage(Null.getImage(), x+17, y-35,null);
    
    }

    public static void inicializaArbol(Graphics g, int x, int y, String i) {
        System.out.println("inicializado");

        g.drawString(i, x, y);
        
        
    }
    //--
    public int totalDerecho(Nodo nodo){
        if (nodo.clave == 0){
            return 1;
        }
        else {
           return 1 + totalDerecho(nodo.hijoDerecho);
        }
        
    }
    
    public int totalIzquierdo(Nodo nodo){
        if (nodo.clave == 0){
            return 1;
        }
        else {
           return 1 + totalIzquierdo(nodo.hijoIzquierdo);
        }
        
    }
    
    
}


