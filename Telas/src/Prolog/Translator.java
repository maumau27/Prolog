package Prolog;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import Game.Acao;
import Game.Cor;
import Game.GameController;
import Game.GameState;
import Game.Main;
import Game.Sinal;
import Game.TipoAcao;

public class Translator {
	private Prolog prolog;
	private Query query;
	
	public Translator()
	{
		this.prolog = new Prolog();
	}
	
	public int getJogadasRestantes()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("turno(_, X).");
		solution = query.allSolutions();
		
		return java.lang.Integer.parseInt( String.valueOf(solution[0].get("X")) );
	}
	
	public Cor getJogadorDaVez()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("turno(X, _).");
		solution = query.allSolutions();
		
		switch (String.valueOf(solution[0].get("X"))) 
		{
			case "verde":
				return Cor.verde;
			case "azul":
				return Cor.azul;
			case "vermelho":
				return Cor.vermelho;
			default:
				return Cor.inexistente;
		}
	}
	
	public Sinal getSinalTela(int IdTela)
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("tela("+ IdTela +", _, X).");
		solution = query.allSolutions();
		
		switch (String.valueOf(solution[0].get("X"))) 
		{
			case "acesa":
				return Sinal.acesa;
			case "apagada":
				return Sinal.apagada;
			default:
				return Sinal.inexistente;
		}
	}
	
	public Cor getCorTela(int IdTela)
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("tela("+ IdTela +", X, _).");
		solution = query.allSolutions();
		
		switch (String.valueOf(solution[0].get("X"))) 
		{
			case "verde":
				return Cor.verde;
			case "azul":
				return Cor.azul;
			case "vermelho":
				return Cor.vermelho;
			default:
				return Cor.inexistente;
		}
	}
	
	public Cor getVencedor()
	{
		Map<String, Term>[] solution;
		
		try {
			this.query = this.prolog.doQuery("checa_vencedor(V).", true);
			solution = query.allSolutions();
			switch (String.valueOf(solution[0].get("V"))) 
			{
				case "azul":
					GameController.chageGameState(GameState.WINNER);
					return Cor.azul;
				case "vermelho":
					GameController.chageGameState(GameState.WINNER);
					return Cor.vermelho;
				default:
					return Cor.inexistente;
			}
		} catch (Exception e) {
			return Cor.inexistente;
		}
	}
	
	public Cor getCorIa()
	{
		Map<String, Term>[] solution;
		
		try {
			this.query = this.prolog.doQuery("cor_ia(V).", true);
			solution = query.allSolutions();
			switch (String.valueOf(solution[0].get("V"))) 
			{
				case "azul":
					return Cor.azul;
				case "vermelho":
					return Cor.vermelho;
				default:
					return Cor.inexistente;
			}
		} catch (Exception e) {
			return Cor.inexistente;
		}
	}
	
	public String getDecisaoIa()
	{
		Map<String, Term>[] solution;
		
		try {
			this.query = this.prolog.doQuery("decidir(X).", true);
			solution = query.allSolutions();
			
			return String.valueOf(solution[0].get("X"));
		} catch (Exception e) {
			return "Sem Opção";
		}
	}
	
	public int getTrocaCorRecente()
	{
		Map<String, Term>[] solution;
		
		try {
			this.query = this.prolog.doQuery("troca_cor_recente(X).", true);
			solution = query.allSolutions();
			
			return java.lang.Integer.parseInt( String.valueOf(solution[0].get("X")) );
		} catch (Exception e) {
			return 0;
		}
	}
	
	public boolean doAcao(Acao acao)
	{
		try {
			this.prolog.doQuery(acao.getString(), true);
			GameController.updatePlayerLock();
			GameController.frame.reDraw();
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void setCorIa(Cor cor)
	{
		this.prolog.doQuery("assert(cor_ia("+cor.toString()+")).");
	}
}
