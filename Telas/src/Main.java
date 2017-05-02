import java.util.Map;

import org.jpl7.*;

import Draw.TelaPrincipal;
import Prolog.Prolog;
import Prolog.Translator;

public class Main {
	private boolean isRunning = true;
	
	public static void main(String[] args)
	{
//		Prolog p = new Prolog();
//		Query query;
//		Map<String, Term>[] solution;
//		
//		query = p.doQuery("tela(X, Y, Z).");
//		solution = query.allSolutions();
//		
//		for( int i = 0 ; i < solution.length ; i++ ) {
//			
//			System.out.println( "Prolog CMD: x: " + String.valueOf(solution[i].get("X")) + " | y: " + String.valueOf(solution[i].get("Y")) + " | T : " + String.valueOf(solution[i].get("Z")) );
//		}
		
		Translator tr = new Translator();
		
		System.out.println(tr.getDecisaoIa());
		
//		TelaPrincipal t = new TelaPrincipal("Teste");
//		t.setVisible(true);
		
		
	}
	
	public void gameLoop()
	{
		gameLogic();
		try
		{
			Thread.sleep(100);
		}
		catch (Exception e) {
			System.err.println(e);
		}
		if(isRunning)
		{
			gameLoop();
		}
	}
	
	public void gameLogic()
	{
		
	}
}
