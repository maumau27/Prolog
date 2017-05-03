package Game;

import java.util.Scanner;

import Prolog.Translator;

public final class Controller {
	
	private static GameState gameState = GameState.GAME;
	public static Translator translator = new Translator();
	
	public static void gameLogic()
	{
		switch (Controller.gameState) 
		{
			case MAIN_MENU:
				
				break;
			
			case GAME:
				System.out.println("IA jogou");
				translator.doAcao(translator.getDecisaoIa());
				/*if(translator.getJogadorDaVez() == translator.getCorIa())
				{
					System.out.println("IA jogou");
					translator.doAcao(translator.getDecisaoIa());
				}
				else
				{
					Scanner scanner = new Scanner(System.in); 
					String s = scanner.nextLine();
					System.out.println(translator.doAcao(s));
				}*/
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
