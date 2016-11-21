//import jpl.*;
import org.jpl7.*;
import java.lang.System;
//import java.util.Hashtable;
import java.util.Map;

public class Prolog 
{
	
  public static void main(String[] args) 
  {
	  
    Query q1 = new Query("consult", new Term[] {new Atom("Cores.pl")});
    
    System.out.println("consult " + (q1.hasSolution() ? "succeeded" : "failed"));
    
    Query q2 = new Query("tela((X,Y), Cor, Sinal)");
    
    //		 Hashtable[] solution = q2.allSolutions();	
    
	 Map<String, Term>[] solution = q2.allSolutions();		
	 
	 if (solution != null) 
	 {	
	   for (int i = 0; i < solution.length; i++)
	   {
			System.out.println("X = " + solution[i].get("X"));
			System.out.println("Y = " + solution[i].get("Y"));
			System.out.println("Cor = " + solution[i].get("Cor"));
			System.out.println("Sinal = " + solution[i].get("Sinal"));
	   }
	 }
	 
  }	
  
}