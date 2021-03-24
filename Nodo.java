/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quiz_2;

/**
 *
 * @author Hector Garcia
 */
public class Nodo {
    
    private Nodo hijoIzq, hijoDer;
    private int height, data;
    
    /**
     *
     * @param info
     */
    public Nodo(int info){
        this.hijoIzq = this.hijoDer = null;
        this.height = 0;
        this.data = info;
    }

    /**
     *
     * @return
     */
    public Nodo getHijoIzq() {
        return hijoIzq;
    }

    /**
     *
     * @param hijoIzq
     */
    public void setHijoIzq(Nodo hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    /**
     *
     * @return
     */
    public Nodo getHijoDer() {
        return hijoDer;
    }

    /**
     *
     * @param hijoDer
     */
    public void setHijoDer(Nodo hijoDer) {
        this.hijoDer = hijoDer;
    }

    /**
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     *
     * @return
     */
    public int getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(int data) {
        this.data = data;
    }
    
    
}
