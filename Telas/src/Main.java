import java.util.Map;

import org.jpl7.*;

import Draw.*;
import Game.*;
import Prolog.Prolog;
import Prolog.Translator;

public class Main {
	private static boolean isRunning = true;
	
	public static void main(String[] args)
	{
		Frame t = new Frame("Teste");
		t.setVisible(true);
		Controller.translator.setCorIa(Cor.azul);
		
		try
		{
			Thread.sleep(1800);
		}
		catch (Exception e) {
			System.err.println(e);
		}
		
		gameLoop();
	}
	
	public static void gameLoop()
	{
		Controller.gameLogic();
		try
		{
			Thread.sleep(1200);
		}
		catch (Exception e) {
			System.err.println(e);
		}
		if(isRunning)
		{
			gameLoop();
		}
	}
}
