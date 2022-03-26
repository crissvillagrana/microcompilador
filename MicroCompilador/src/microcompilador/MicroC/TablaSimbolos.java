package microcompilador.MicroC;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author estef
 */
public class TablaSimbolos {
    //Atributos
  int idEntrada;
  String nombreDato;
  String tipoDato;
  String valor;

  public TablaSimbolos(){
      idEntrada=0;
      nombreDato="";
      tipoDato="";
      valor="";
    }

    public int getIdEntrada() {
        return idEntrada;
    }

    public void setIdEntrada(int idEntrada) {
        this.idEntrada = idEntrada;
    }

    public String getNombreDato() {
        return nombreDato;
    }

    public void setNombreDato(String nombreDato) {
        this.nombreDato = nombreDato;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
  
    @Override
    public String toString(){
      return idEntrada+nombreDato+tipoDato+valor;
    }

}
