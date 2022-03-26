package microcompilador.MicroC;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.*;

/**
 *
 * @author User
 */
public class Arbol {
    //Atributos
    Stack<Nodo> ArbolNodo = new Stack<Nodo>();
    Stack<String> caracter = new Stack<String>();
    final String espacios="\t";
    final String aritmeticos = "+-*()/";
    private Nodo raiz;
    HashMap<String, String> tablaSimbolos;
    HashMap<String, String> erroresSemanticos;
    HashMap<String, String> producciones;
    HashMap<String, String> reglasSemanticas;
    int paso;
    String r,reglaSemantica;
    ArrayList<String> reglasEjecutadas;
    HashMap<String, TablaSimbolos> tablaSimbolosSemantica;
    
    //Metodos
    public Arbol(){
        tablaSimbolos = new HashMap<>();
        erroresSemanticos = new HashMap<>();
        producciones = new HashMap<>();
        reglasSemanticas = new HashMap<>();
        paso=0;
        reglaSemantica="";
        r="";
        //reglasEjecutadas = new ArrayList<String>();
        tablaSimbolosSemantica = new HashMap<String,TablaSimbolos>();
        reglasEjecutadas = new ArrayList<String>();
       
    }
    
    public void agregaValex(String lexema, String valor){
        tablaSimbolos.put(lexema, valor);
    }
    
    public String regresaValex(String lexema){
        return tablaSimbolos.get(lexema);
    }
    
    public void agregaProducciones(){
        producciones.put("r1","E.nodo = new Nodo('+',E1.nodo,T.nodo)");
        producciones.put("r2","E.nodo = new Nodo('-',E1.nodo,T.nodo)");
        producciones.put("r3","E.nodo = T.nodo");
        producciones.put("r4","T.nodo = E.nodo");
        producciones.put("r5","T.nodo = new Hoja(id,id.entrada)");
        producciones.put("r6","T.nodo = new Hoja(num,num.id)");
     }//agregaProducciones
    
    public String getTablaProducciones(){
            String cadena="";
        for (String i : producciones.keySet()) 
            cadena+="key: "+i+" value: "+producciones.get(i)+"\n";
   
        return cadena;
    }//getTabla->Retorna la tabla de símbolos completa

    public void agregaReglasS(String lexema, String valor){
        reglasSemanticas.put(lexema,valor);
    }
    public String getReglasS(){
        String cadena="";
        for (String i : reglasSemanticas.keySet())
            cadena+="key: "+i+" value: "+reglasSemanticas.get(i)+"\n";
      return cadena;
    }    

    public String regresaProduccion(String lexema){//F.val=digito.valex
        return producciones.get(lexema);
    }//Valex- Análisis semántico

    public String getReglasEjecutadas(){
        String reglasE="";
        for(int i=0; i< reglasEjecutadas.size();i++){
          System.out.println("Reglas ejecutadas: "+reglasEjecutadas.get(i));
          reglasE+=reglasEjecutadas.get(i)+"\n";
        }
      return reglasE; 
    }


    private void guardar(){
        //MÉTODO PARA LOS OPERADORES  - 24/03/2022
        paso++;
        r="r"+paso;
        //reglaSemantica=regresaProduccion(r);
        String regla="E.nodo = new Nodo('+',E1.nodo,T.nodo)";
        agregaReglasS("1",regla);
        Nodo izquierdo = (Nodo) ArbolNodo.pop();
        Nodo derecho = (Nodo) ArbolNodo.pop();
        String operador = caracter.peek();
        ArbolNodo.push(new Nodo(derecho, caracter.pop(), izquierdo));
        if(operador.equals("+")){ //SUMA
         String reglaE = "E.nodo = new Nodo(+,E1.nodo,T.nodo)";
         reglasEjecutadas.add("p"+paso+" "+reglaE);
        }
        if(operador.equals("*")){ //MULTIPLICACION
           String reglaE="E.nodo = new Nodo(*,E1.nodo,T.nodo)";
           reglasEjecutadas.add("p"+paso+" "+reglaE);
        }
        if(operador.equals("-")){ //RESTA
           String reglaE="E.nodo = new Nodo(-,E1.nodo,T.nodo)";
           reglasEjecutadas.add("p"+paso+" "+reglaE);
        }
        if(operador.equals("/")){ //DIVISIÓN
           String reglaE="E.nodo = new Nodo(/,E1.nodo,T.nodo)";
           reglasEjecutadas.add("p"+paso+" "+reglaE);
        }
    }//guardar
    
    public Nodo crear(String expresion){
        StringTokenizer tokenizer;
        String token;
        paso=0;
        String produccion="",p="";
        agregaProducciones();
        System.out.println("producciones "+getTablaProducciones());
        tokenizer = new StringTokenizer(expresion, espacios+aritmeticos,true);
        while(tokenizer.hasMoreTokens()){
            token = tokenizer.nextToken();
            System.out.println("Token: "+token);
            if(espacios.indexOf(token)>=0){
                //Omitir espacios
            }else if(aritmeticos.indexOf(token)<0){
                ArbolNodo.push(new Nodo(token));
                paso++;
                //p="p"+paso;
                //produccion=regresaProduccion(p);
                //agregaReglasS(p,produccion);
                String regla = "T.nodo = new Hoja(id<"+token+">,id.entrada)";
                reglasEjecutadas.add("p"+paso+" "+regla);
                //r="r"+paso;
                //reglaSemantica=regresaProduccion(r);
                //agregaReglasS("5",regla);
                //Aqui agregar la regla semantica
                //agregaValex("p"+paso, produccion);
            }else if(token.equals(")")){
                while(!caracter.empty()&&!caracter.peek().equals("(")){
                    guardar();
                }//while
                caracter.pop();
            }else{
                if(!token.equals("(")&&!caracter.empty()){
                    String exa = (String)caracter.peek();
                    while(!exa.equals("(")&&caracter.empty()&&aritmeticos.indexOf(exa)>=aritmeticos.indexOf(token)){
                        guardar();
                        if(!caracter.empty()){
                            exa = (String)caracter.peek();
                        }//if
                    }//while
                }//if token
                caracter.push(token);
            }
        }//while-tokenizer
        while(!caracter.empty()){
            if(caracter.peek().equals("(")){
                caracter.pop();
            }else{
                guardar();
                raiz = (Nodo)ArbolNodo.peek();
            }//else
        }//while
        return raiz;
    }//crear
}
