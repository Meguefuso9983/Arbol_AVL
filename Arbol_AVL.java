package quiz_2;

import javax.swing.JOptionPane;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;

/**
 *
 * @author Hector Garcia
 */
public class Arbol_AVL {
    
    private Nodo root;
    
    /**CONSTRUCTOR
     *
     */
    public Arbol_AVL(){
        
        this.root = null;
        
    }

    /**
     *
     * @return RAIZ DE ARBOL
     */
    public Nodo getRoot() {
        return root;
    }

    /**CAMBIA LA RAIZ
     *
     * @param root
     */
    public void setRoot(Nodo root) {
        this.root = root;
    }
    
    public boolean isEmpty(){
        return root == null;
    }
    
    public void AllEmpty(){
        this.root = null;
    }
    


    /** RECORRER ARBOL DE MENOR A MAYOR
     *
     * @param rz
     */

    
    public void Recorrido_InOrden(Nodo rz){
        
        if (rz != null) {  
            Recorrido_InOrden(rz.getHijoIzq());
            System.out.println(rz.getData());
            Recorrido_InOrden(rz.getHijoDer());

        }
        
    }
    

    /**RETORNA LA ALTURA DEL NODO PARAMETRO
     *
     * @param rz
     * @return ALTURA
     */

    
    public int Height(Nodo rz){
        if (rz == null) {
            return 0;            
        }
        return rz.getHeight();
    }
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public int Maximo(int x, int y){
        return (x > y) ? x : y;
    }
    
    /**
     *
     * @param rz
     * @return
     */
    public Nodo Minimo(Nodo rz){
        Nodo act = rz;
        
        while(act.getHijoIzq() != null){
            act = act.getHijoIzq();      
        }
        
        return act;
    }
    
    /** VALIDA SI EL ARBOL ESTA balanceado
     *
     * @param rz
     * @return FACTOR DE EQUILIBRIO
     */
    public int Balanceo(Nodo rz){
        if (rz == null) {
            return 0;
        }
        return Height(rz.getHijoIzq()) - Height(rz.getHijoDer());
        
    }
    
    /**BUSCA AL PADRE DE NODO PASADO COMO PARAMETRO
     *
     * @param rz
     * @param info
     * @return PADRE
     */
    public Nodo BuscarPadre(Nodo rz, int info){
        Nodo aux = null;
        
        if (info < rz.getData()) {
            if (aux != null) {
                return aux;                
            } else if (rz.getHijoIzq() != null) {
                aux = BuscarPadre(rz.getHijoIzq(), info);
            } else{
                return rz;
            }
            
        } else if (info > rz.getData()) {
            if (aux != null) {
                return aux;                
            } else if (rz.getHijoDer() != null) {
                aux = BuscarPadre(rz.getHijoDer(), info);                
            } else{
                return rz;
            }     
        }
               
        return aux;
    }
    
    /**ROTACIÓN A LA DERECHA
     *
     * @param rz
     * @return 
     */
    public Nodo RotationRight(Nodo rz){
    
        Nodo aux = rz.getHijoIzq();
        Nodo nuevo = aux.getHijoDer();
        
        aux.setHijoDer(rz);
        rz.setHijoIzq(nuevo);
        
        rz.setHeight(Maximo(Height(rz.getHijoIzq()), Height(rz.getHijoDer())) + 1);
        aux.setHeight(Maximo(Height(aux.getHijoIzq()), Height(aux.getHijoDer())) + 1);
        
        return aux;

    }
    
    /**ROTACION A LA IZQUIERDA
     *
     * @param rz
     * @return
     */
    public Nodo RotationLeft(Nodo rz){
   
        Nodo aux = rz.getHijoDer();
        Nodo nuevo = aux.getHijoIzq();
        
        aux.setHijoIzq(rz);
        rz.setHijoDer(nuevo);
        
        rz.setHeight(Maximo(Height(rz.getHijoDer()), Height(rz.getHijoIzq())) + 1);
        aux.setHeight(Maximo(Height(aux.getHijoDer()), Height(rz.getHijoIzq())) + 1);
            
        return aux;      
    } 
    
    /**BUSCA UN NODO
     *
     * @param rz
     * @param info
     * @return NODO ENCONTRADO
     */
    public Nodo BuscarNodo(Nodo rz, int info){
        
        if (rz == null) {
            return null;            
        } else{
            if (rz.getData() == info) {
                return rz;                
            } else {
                if (rz.getData() > info) {
                    return BuscarNodo(rz.getHijoIzq(), info);                    
                } else{
                    return BuscarNodo(rz.getHijoDer(), info);
                }
            }
        }
      
    }
    
    /**INSERTA NODO EN EL ARBOL CUMPLIENDO CON LAS CONDICIONES DEL AVL
     *
     * @param rz
     * @param info
     * @return
     */
    public Nodo Insert(Nodo rz, int info){
        
        if (rz == null) {
            return new Nodo(info);            
        }else {
            Nodo padre = this.BuscarPadre(rz, info);
            if (padre == null) {
                JOptionPane.showMessageDialog(null, "EL ELEMENTO YA EXISTE! ");
            }else {
                if (info < rz.getData()) {
                    rz.setHijoIzq(Insert(rz.getHijoIzq(), info));
                } else if (info > rz.getData()) {
                    rz.setHijoDer(Insert(rz.getHijoDer(), info));
                }
                rz.setHeight(Maximo(Height(rz.getHijoIzq()), Height(rz.getHijoDer())) + 1);
                int balanceado = Balanceo(rz);
                
                if (balanceado == 0 || balanceado == 1 || balanceado == -1) {
                    return rz;
                } else{
                    if (balanceado > 1 && info < rz.getHijoIzq().getData()) {
                        return RotationRight(rz);            
                    }    
                    if (balanceado < -1 && info > rz.getHijoIzq().getData()) {
                        return RotationLeft(rz);
                    }
                    if (balanceado > 1 && info > rz.getHijoIzq().getData()) {
                        rz.setHijoDer(RotationLeft(rz.getHijoDer()));
                        return RotationRight(rz);
                    }
                    if (balanceado < -1 && info < rz.getHijoDer().getData()) {
                        rz.setHijoDer(RotationRight(rz.getHijoDer()));
                        return RotationLeft(rz);
                    }
                }
            }
        }
 
        return rz;

    }
     
    /**ELIMINA EL NODO PARAMETRO Y MANTIENE EL EQUILIBRIO DEL ARBOL
     *
     * @param rz
     * @param info
     * @return
     */
    public Nodo Delete(Nodo rz, int info){
        
        if (rz == null) {
            return rz;
        }
        if (info < rz.getData()) {
            rz.setHijoIzq(Delete(rz.getHijoIzq(), info));
        } else if (info > rz.getData()) {
            rz.setHijoDer(Delete(rz.getHijoDer(), info));
        } else{
            if (rz.getHijoIzq() == null || rz.getHijoDer() == null) {
                    
                Nodo temp = null;
                    
                if (temp == rz.getHijoIzq()) {
                    temp = rz.getHijoDer();
                } else{
                    temp = rz.getHijoIzq();
                }
                    
                if (temp == null) {
                    temp = rz;
                    rz = null;
                } else {
                    rz = temp;
                }
            } else{
                Nodo temp = Minimo(rz.getHijoDer());
                    
                rz.setData(temp.getData());
                    
                rz.setHijoDer(Delete(rz.getHijoDer(), temp.getData()));
            }
        }
        
        if (rz == null) {
            return rz;
        }
        
        rz.setHeight(Maximo(Height(rz.getHijoDer()), Height(rz.getHijoIzq())) + 1);
        
        int balanceado = Balanceo(rz);
        if (balanceado == 0 || balanceado == 1 || balanceado == -1) {
            return rz;
        }else {
            if (balanceado > 1 && Balanceo(rz.getHijoIzq()) >= 0) {
                return RotationRight(rz);
            }
            if (balanceado > 1 && Balanceo(rz.getHijoIzq()) < 0) {
                rz.setHijoIzq(RotationLeft(rz.getHijoDer()));
                return RotationRight(rz);
            }
        
            if (balanceado < -1 && Balanceo(rz.getHijoDer()) <= 0) {
                return RotationLeft(rz);
            }
        
            if (balanceado < -1 && Balanceo(rz.getHijoDer()) > 0) {
                rz.setHijoDer(RotationRight(rz.getHijoDer()));
                return RotationLeft(rz);
            }    
        } 
           
        return rz;
       
    }
    
    
    /**GRAFICA EL ARBOL VACIO
     *
     * @return 
     * @
     */
    public Graph CrearGrafica_arbol(){
        
        System.setProperty("org.graphstream.ui", "swing");
        
        Graph graph = new SingleGraph("ARBOL AVL");
        graph.setAttribute("ui.stylesheet", "graph { padding: 40px; } node { size: 20px; fill-color: red, orange; fill-mode: gradient-horizontal; text-alignment: at-right; text-padding: 3px, 2px; text-background-mode: rounded-box; text-background-color: #EB2; text-color: #222; }");
            
        Node a = graph.addNode("A");
        a.setAttribute("ui.label", "ÁRBOL VACIO"); 
   
        graph.display();
        
        return graph;
    }
    
}
