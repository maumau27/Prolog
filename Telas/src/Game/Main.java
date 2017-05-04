package Game;
import java.util.Map;

import org.jpl7.*;

import Draw.*;
import Game.*;
import Prolog.Prolog;
import Prolog.Translator;

public class Main {
	private static boolean isRunning = true;
	public static Frame frame = new Frame("Teste");
	
	
	public static void main(String[] args)
	{
		frame.setVisible(true);
		GameController.translator.setCorIa(Cor.vermelho);
		
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
		GameController.gameLogic();
		Main.frame.reDraw();
		try
		{
			Thread.sleep(1800);
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
