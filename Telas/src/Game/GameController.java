package Game;

import java.util.Scanner;

import Prolog.Translator;

public final class GameController {
	
	private static GameState gameState = GameState.GAME;
	public static Translator translator = new Translator();
	
	public static void gameLogic()
	{
		switch (GameController.gameState) 
		{
			case MAIN_MENU:
				
				break;
			
			case GAME:
				if(translator.getJogadorDaVez() == translator.getCorIa())
				{
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
}
