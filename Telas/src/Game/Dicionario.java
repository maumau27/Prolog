package Game;
import java.awt.Color;

public class Dicionario {
	
	public Dicionario()
	{
		
	}
	
	public static Color Cor_Color(Cor c)
	{
		switch (c) {
			case verde:
				return Color.GREEN; 
				
			case vermelho:
				return Color.RED;

			case azul:
				return Color.BLUE;

			default:
				return Color.BLACK;
		}
	}
	
	public static TipoAcao String_TipoAcao(String s)
	{
		switch (s) {
			case "troca_sinal":
				return TipoAcao.troca_sinal;
				
			case "troca_cor":
				return TipoAcao.troca_cor;
	
			default:
				return TipoAcao.inexistente;
		}
	}
	
	public static Direcao String_Direcao(String s)
	{
		switch (s) {
			case "direita":
				return Direcao.direita;

			case "esquerda":
				return Direcao.esquerda;
	
			default:
				return Direcao.inexistente;
		}
	}
	
	public static Sinal String_Sinal(String s)
	{
		switch (s) {
			case "apagada":
				return Sinal.apagada;
				
			case "acesa":
				return Sinal.acesa;
	
			default:
				return Sinal.inexistente;
		}
	}

}
