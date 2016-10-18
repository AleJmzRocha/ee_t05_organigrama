package ee_t05_organigrama;

import ListaSimplementeLigada.*;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ArbolGeneral<T> extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	NodoArbol<T> raiz;

	public NodoArbol<T> getRaiz() {
		return raiz;
	}

	public void setRaiz(NodoArbol<T> raiz) {
		this.raiz = raiz;
	}
	
	public static void arbolToString(NodoArbol<String> raiz){
		  System.out.print("("+raiz.getDato()+",");
		  Nodo<NodoArbol<String>> aux = raiz.getHijos().getInicio() ;
		  while(aux != null){
			  arbolToString(aux.getDato());
			  aux = aux.getSiguiente();
		  }
		  System.out.print(")");
		}
	
	public static void recorreR(NodoArbol<String> raiz){
	  System.out.println(raiz.getDato());
	  Nodo<NodoArbol<String>> aux = raiz.getHijos().getInicio() ;
	  while(aux != null){
		  recorreR(aux.getDato());
		  aux = aux.getSiguiente();
	  }
	}
	
	public static String reduceCadena(String cadena){
		int cont = 0;
		int x = 0;
		do{
			
			switch (cadena.charAt(x)){
			case '(': cont++;
			  break;
			case ')': cont--;
			  break;
			}
			x = x+1;
		}while(cont > 0);
		return cadena.substring(3,x);
	}
	
	public static int obtenPosreduceCadena(String cadena){
		int cont = 0;
		int x = 0;
		do{
			
			switch (cadena.charAt(x)){
			case '(': cont++;
			  break;
			case ')': cont--;
			  break;
			}
			x = x+1;
		}while(cont > 0);
		return x;
	}
	
	public static ListaSimplementeLigada<String> obtenHijos(String cadena){
		int cont = 0;
		int x = 0;
		ListaSimplementeLigada<String> resultado = new ListaSimplementeLigada<String>();
		do{
			do{				
				switch (cadena.charAt(x)){
				case '(': cont++;
				  break;
				case ')': cont--;
				  break;
				}
				x = x+1;
			}while(cont > 0 && cont !=-1);
			if (cont != -1){
			  resultado.inserta_final(cadena.substring(0,x));
			  cadena = cadena.substring(x);
			  x=0;
			}
		}while(cont != -1);
		
		return resultado;
	}
	
	
	public static NodoArbol<String> creaArbolDeString(String cadena){
		// (A,(B,(C,)(D,))
		// (B,(C,)(D,))
		//(C,)(D,))
		// (A,(B,))
		// (A,(B,(Z,)(Y,))(C,)(D,))
		
		NodoArbol<String> nuevoArbol = null;
		nuevoArbol = new NodoArbol<String>();
		nuevoArbol.setDato(String.valueOf(cadena.charAt(1)));
		if (cadena.length() >= 3)
		  cadena = cadena.substring(3);
		ListaSimplementeLigada<String> hijos = obtenHijos(cadena);
		Nodo<String> aux = hijos.getInicio();
		while(aux != null){			
		    nuevoArbol.getHijos().inserta_final(creaArbolDeString(aux.getDato()));
		    aux = aux.getSiguiente();		  
		}		
		return nuevoArbol;		
	}

	@Override
	public void paint(Graphics h){
		super.paintComponent(h);
		this.setBackground(Color.WHITE);
		
		h.setColor(Color.BLACK);
		h.drawLine(350, 90, 1000, 90);
		
		h.setColor(Color.BLUE);
		h.drawRect(630, 25, 100, 60);
		h.drawString("A", 680, 60);
		
		h.setColor(Color.BLUE);
		h.drawRect(300, 100, 100, 60);
		h.drawString("B", 350, 135);
		
		h.setColor(Color.BLUE);
		h.drawRect(950, 100, 100, 60);
		h.drawString("C", 990, 135);
		
		h.setColor(Color.BLACK);
		h.drawLine(120, 170, 550, 170);
		
		h.setColor(Color.BLACK);
		h.drawLine(800, 170, 1150, 170);
	}
	
	public static void main(String[] args) {
//		Nodo<String> raiz = new Nodo<String>("Director");
//		raiz.getHijos().inserta_inicio(new Nodo<String>("Subdirector 01"));
//		raiz.getHijos().inserta_inicio(new Nodo<String>("Subdirector 02"));
//		raiz.getHijos().inserta_inicio(new Nodo<String>("Subdirector 03"));
//		raiz.getHijos().inserta_inicio(new Nodo<String>("Subdirector 04"));
//	    raiz.getHijos().obten_ultimo().getDato().getHijos().inserta_inicio(new Nodo<String>("Sub Subdirector 01"));
	    
//	    recorreR(raiz);
	    NodoArbol<String> raiz = creaArbolDeString("(A,(B,(D,(F,))(G,))(C,))");
	    arbolToString(raiz);
		//System.out.println(obtenHijos(")"));
		//(Z,)(Y,))(C,)(D,))		
	    
	    JFrame jf = new JFrame("Árbol");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArbolGeneral<String> f = new ArbolGeneral<String>();
		f.setBackground(Color.WHITE);
		jf.add(f);
		jf.setSize(1360,735);
		jf.setVisible(true);
	}
}