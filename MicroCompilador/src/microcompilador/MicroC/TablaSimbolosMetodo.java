/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package microcompilador.MicroC;

/**
 *
 * @author barce
 */
public class TablaSimbolosMetodo {
    class Nodo {
        int idEntrada;
        String nombreDato;
        String tipoDato;
        String valor;
        Nodo sig;
    }
	
    private Nodo raiz;
    
    public TablaSimbolosMetodo() {
        raiz=null;
    }
    
    public void insertar(int id, String nD, String tD, String valor) {
    	Nodo nuevo;
        nuevo = new Nodo();
        nuevo.idEntrada = id;
        nuevo.nombreDato = nD;
        nuevo.tipoDato = tD;
        nuevo.valor = valor;
        if (raiz==null)
        {
            nuevo.sig = null;
            raiz = nuevo;
        }
        else
        {
            nuevo.sig = raiz;
            raiz = nuevo;
        }
    }
    
    public int extraer ()
    {
        if (raiz!=null)
        {
            int id = raiz.idEntrada;
            raiz = raiz.sig;
            return id;
        }
        else
        {
            return Integer.MAX_VALUE;
        }
    }
    
    public String imprimir() {
        Nodo reco=raiz;
        String codigo = "";
        while (reco!=null) {
            //System.out.print(reco.info+"-");
            if(reco.sig!=null){
                codigo +=("param "+reco.nombreDato+"\n");
            }else{
                codigo +=("call "+reco.nombreDato+"\n");
            }
            reco=reco.sig;
        }
        return codigo;
    }
}
