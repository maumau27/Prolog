import java.util.Map;

import org.jpl7.*;

import Draw.TelaPrincipal;
import Prolog.Prolog;

public class Main {
	public static void main(String[] args)
	{
		Prolog p = new Prolog();
		Query query;
		Map<String, Term>[] solution;
		
		query = p.doQuery("tela(X, Y, Z).");
		solution = query.allSolutions();
		
		for( int i = 0 ; i < solution.length ; i++ ) {
			
			System.out.println( "Prolog CMD: x: " + String.valueOf(solution[i].get("X")) + " | y: " + String.valueOf(solution[i].get("Y")) + " | T : " + String.valueOf(solution[i].get("Z")) );
		}
		
		TelaPrincipal t = new TelaPrincipal("Teste");
		t.setVisible(true);
	}
}
