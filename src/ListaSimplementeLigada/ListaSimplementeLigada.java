package ListaSimplementeLigada;
import javax.swing.*;
//los comentarios sirven para documentar  código y generar el javadoc
/**
 * Esta clase implementa una lista simplemente ligada
 * @author Ale Rocha
 *
 */

public class ListaSimplementeLigada<T extends Comparable<T>>{
	/**
	 * Este es el inicio de la lista
	 */
    private Nodo<T> inicio;
       
    public ListaSimplementeLigada(){
    	inicio = null;
    }
    
    public void agregarInicio(T dato){
        Nodo<T> nuevo = new Nodo<T>(dato);
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
    }
    
    public void inserta_final(T dato){
        Nodo<T> temporal = inicio;
        if(inicio == null)
           agregarInicio(dato);
        else{
             while(temporal.getSiguiente() != null)
                   temporal = temporal.getSiguiente();
             temporal.setSiguiente(new Nodo<T>(dato));
        }        
    }
    
    public void recorre(){
    	Nodo<T> temporal = inicio;
    	while(temporal != null){
    		System.out.print(temporal.getDato() + "-->");
    		temporal = temporal.getSiguiente();
    	}
    	System.out.print("null");
    }
    
    public void recorrerRecursivo(Nodo<T> p){
    	if(p != null){
    		System.out.println(p.getDato());
    		recorrerRecursivo(p.getSiguiente());
    	}
    }
    //Una limitante puede ser un StackOverFlow porque usa una pila, 
    //en cambio la iterativa usa un contador
    
    public void insertarAntesDe(T dato, T referencia){
        Nodo<T> temporal = inicio, nodoEncontrado = null, nuevo;
        Boolean band = true;
        while(temporal.getDato() != referencia && band)
              if(temporal.getSiguiente() != null){
                  nodoEncontrado = temporal;
                  temporal = temporal.getSiguiente();
              }else
                  band = false;
                  
        if(band)
           if(temporal == inicio)
              agregarInicio(dato);
           else{
               nuevo = new Nodo<T>(dato);
               nuevo.setSiguiente(temporal);
               nodoEncontrado.setSiguiente(nuevo);
            }              
    }
    
    /**
     * Este método sobrescribe el método toString() de la clase Object
     */
    @Override
    public String toString(){
        Nodo<T> iterador = inicio;
        String s = "";
         while( iterador != null ){ 
             s += iterador.getDato() + " --> ";
             iterador = iterador.getSiguiente(); 
         } 
         s += null;
         return s;
    }
    
    public Nodo<T> getInicio(){
        return inicio;
    }
        
    public void insertarDespuesDe(T dato, T referencia){
        Nodo<T> temporal = inicio, nuevo;
        Boolean band = true;
        while(temporal.getDato() != referencia && band)
              if(temporal.getSiguiente() != null)
                 temporal = temporal.getSiguiente();
              else
                 band = false;
        
        if(band)
           if(temporal == inicio)
              agregarInicio(dato);
           else{
               nuevo = new Nodo<T>(dato);
               nuevo.setSiguiente(temporal.getSiguiente());
               temporal.setSiguiente(nuevo);
           }
    }
    
    public void insertaOrdenado(T dato){
        Nodo<T> temporal = inicio, nodoEncontrado = null, nuevo;
        Boolean band = true;
        
        if(inicio == null)
           agregarInicio(dato);
        else{
           while((dato.compareTo(temporal.getDato()))<0 && band)
                 if(temporal.getSiguiente() != null){
                    nodoEncontrado = temporal;
                    temporal = temporal.getSiguiente();
                }else
                    band = false;
                    
           if(band)
              if(temporal == inicio)
                 agregarInicio(dato);
              else{
                 nuevo = new Nodo<T>(dato);
                 nuevo.setSiguiente(temporal);
                 nodoEncontrado.setSiguiente(nuevo);
              }
           else
               inserta_final(dato);
        }
    }
    
    public Nodo<T> busca(T m){
        Nodo<T> aux = inicio;
        while(aux != null){
              if(aux.getDato().compareTo(m) == 0)
                 return aux;
              aux = aux.getSiguiente();
        }                 
        return null;
    }
    
    public Integer longitud(){
        Integer elementos = 0;
        Nodo<T> aux = inicio;
        while(aux != null){
              elementos++;
              aux = aux.getSiguiente();
        }                 
        return elementos;
    }
    public T eliminaFinalDevolviendo(){
        Nodo<T> temporal = inicio, anterior = null;
        try{
            if(inicio.getSiguiente() == null)
               inicio = null;
               else{
                   while(temporal.getSiguiente() != null){
                       anterior = temporal;
                       temporal = temporal.getSiguiente();
                    }                
                    anterior.setSiguiente(null);
            }     
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null,"Lista vacía");
        }
        return temporal.getDato();
    }
    //Elimar final, eliminar inicio
    public static void main(String[] args){
        ListaSimplementeLigada<Integer> lista = new ListaSimplementeLigada<Integer>();
        lista.agregarInicio(5);
        lista.agregarInicio(4);
        lista.agregarInicio(3);
        lista.agregarInicio(2);
        lista.agregarInicio(1);
//        lista.agregarFinal(8);
//        lista.agregarFinal(9);
        lista.insertarAntesDe(6,7);
        lista.insertarAntesDe(24,2);
//        lista.insertarAntesDe(18,5);
//        lista.insertaOrdenado(38);
//        lista.insertaOrdenado(25);
//        lista.insertaOrdenado(3);
//        lista.insertaOrdenado(20);
        //System.out.println(lista);
        //lista.recorre();
    }
}