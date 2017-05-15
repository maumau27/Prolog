package Prolog;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;

import org.jpl7.Query;
import org.jpl7.Term;

import Draw.MainFrame;

public class Translator {
	
	private Prolog prolog;
	private Query query;
	
	public Translator()
	{
		this.prolog = new Prolog();
	}
	
	public ArrayList<Point> getTiles()
	{
		Map<String, Term>[] solution;
		ArrayList<Point> pontos = new ArrayList<>();
		
		this.query = this.prolog.doQuery("mapa(X, Y).");
		solution = query.allSolutions();
		
		for (Map<String, Term> sol : solution) {
			pontos.add(new Point(convertTerm_Int(sol.get("X")), convertTerm_Int(sol.get("Y"))));
		}
		
		return pontos;
	}
	
	public Point getPlayerPos()
	{
		Map<String, Term>[] solution;
		
		this.query = this.prolog.doQuery("posicao_jogador(X, Y).");
		solution = query.allSolutions();
		
		return new Point(convertTerm_Int(solution[0].get("X")), convertTerm_Int(solution[0].get("Y")));
	}
	
	public void doAcao(String acao)
	{
		this.query = this.prolog.doQuery("acao("+ acao +").");
		query.allSolutions();
	}
	
	private int convertTerm_Int(Term t)
	{
		return java.lang.Integer.parseInt(String.valueOf(t));
	}
}
