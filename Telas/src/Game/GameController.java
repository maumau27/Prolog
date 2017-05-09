package Game;

import java.awt.Button;
import java.util.Scanner;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;

import Draw.Frame;
import Draw.Placar;
import Draw.Tela;
import Draw.Menu;
import Prolog.Translator;

public final class GameController {
	
	private static GameState gameState = GameState.NULL;
	public static Translator translator = new Translator();
	public static boolean LockPlayerAction = true;
	public static Frame frame = new Frame("Telas");
	
	public static void gameLogic()
	{
		switch (GameController.gameState) 
		{
			case MAIN_MENU:
				
				break;
			
			case GAME:
				if(translator.getJogadorDaVez() == translator.getCorIa())
				{
					try
					{
						Thread.sleep(1200);
					}
					catch (Exception e) {
						System.err.println(e);
					}
					System.out.println(translator.getDecisaoIa());
					Acao decisao = new Acao(translator.getDecisaoIa());
					translator.doAcao(decisao);
				}
				else
				{

				}
				break;
				
			case COLOR_SELECT:
				
				break;
				
			case WINNER:
				
				break;
	
			default:
				
				break;
		}
	}
	
	public static void chageGameState(GameState newState)
	{
		GameController.gameState = newState;
		
		switch (newState) {
		case GAME:
			
			frame.reset();
			
			frame.addComponent(new Placar());
			for (int i = 1; i < 5; i++) {
				frame.addComponent(new Tela(i));
			}
			
			frame.resetPane();

			frame.reDraw();
			
			break;
			
		case WINNER:

			frame.reset();
			
			frame.addComponent(new Placar());
			
			frame.resetPane();
			
			frame.reDraw();
			
			break;
			
		case MAIN_MENU:
			
			frame.reset();
			
			frame.addComponent(new Menu());
			
			frame.resetPane();
			
			frame.reDraw();
			
			break;

		default:
			break;
		}
	}
	
	public static GameState getGameState()
	{
		return GameController.gameState;
	}
	
	public static void updatePlayerLock()
	{
		if(translator.getJogadorDaVez() == translator.getCorIa())
		{
			GameController.LockPlayerAction = true;
		}
		else
		{
			GameController.LockPlayerAction = false;
		}
	}
}
