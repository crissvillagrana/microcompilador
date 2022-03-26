package microcompilador.MicroC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Nodo {
    private String dato;
    private Nodo padre;
    private Nodo izquierdo;
    private Nodo derecho;
    private String codigoIntermedio;

    public String getCodigo() {
        return codigoIntermedio;
    }

    public void setCodigo(String codigoIntermedio) {
        this.codigoIntermedio = codigoIntermedio;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    private String lugar;

    public Nodo (String informacion){
    this.dato=informacion;
    }// constructor

    public Nodo(Nodo derecho, String dato, Nodo izquierdo){
        this.derecho=derecho;
        this.dato=dato;
        this.izquierdo=izquierdo;
        this.padre=null;
    }// constructor


    public Nodo getPadre(){
        return padre;
    }// constructor

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
    
    public void setPadre(Nodo padre){
        this.padre=padre;
    }
}
