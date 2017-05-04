package Game;
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
		GameController.translator.setCorIa(Cor.vermelho);
		GameController.chageGameState(GameState.GAME);
		GameController.frame.setVisible(true);
		
		gameLoop();
	}
	
	public static void gameLoop()
	{
		GameController.gameLogic();
		GameController.updatePlayerLock();
		//Main.frame.reDraw();
		try
		{
			Thread.sleep(300);
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
